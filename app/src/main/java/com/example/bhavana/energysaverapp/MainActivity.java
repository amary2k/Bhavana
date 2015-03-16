package com.example.bhavana.energysaverapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhavana.energysaverapp.Model.Profile;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends ActionBarActivity {

    public static Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final String mainMenuItems[] = {
                    "My Profile",
                    //"Add Bill",
                    "Scan Bar Code",
                    "Get Bill from Site",
                    "Show Usage Statistics",
                    "Solutions"
                    //"View Carbon Footprints"

            };
            ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_layout,
                    R.id.list_item_menu_item,
                    mainMenuItems);

            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView myListView = (ListView) rootView.findViewById(R.id.listView_menu);
            myListView.setAdapter(myArrayAdapter);
            //final PlaceholderFragment currentView = this;
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemText = (String) ((TextView) view).getText();

                    if(mainMenuItems[0].equalsIgnoreCase(itemText)) {
                        Intent i = new Intent(view.getContext(), ProfileActivity.class);
                        startActivity(i);
                    }
                    // Redirect to BarCode Scanner App
                    else if(mainMenuItems[1].equalsIgnoreCase(itemText)) {
                        IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
                        scanIntegrator.initiateScan();
                    }
                    // Redirect to a website
                    else if(mainMenuItems[2].equalsIgnoreCase(itemText))
                    {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                        startActivity(i);
                    }
                    else if(mainMenuItems[3].equalsIgnoreCase(itemText)) {
                        Intent i = new Intent(view.getContext(), StatisticsActivity.class);
                        startActivity(i);
                    }
                    else if(mainMenuItems[4].equalsIgnoreCase(itemText)) {
                        Intent i = new Intent(view.getContext(), SolutionsActivity.class);
                        startActivity(i);
                    }
                    /*else if (mainMenuItems[5].equalsIgnoreCase(itemText)) {

                        // These two need to be declared outside the try/catch
                        // so that they can be closed in the finally block.
                        HttpURLConnection urlConnection = null;
                        BufferedReader reader = null;

                        // Will contain the raw JSON response as a string.
                        String coolClimateAPIJsonStr = null;
                        // Construct the URL for the OpenWeatherMap query
                        // Possible parameters are avaiable at OWM's forecast API page, at
                        // http://openweathermap.org/API#forecast
                        //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
                        String zipcode = MainActivity.profile.getPostalCode();
                        if (zipcode == null || zipcode.length() == 0) {
                            Toast successToast = Toast.makeText(view.getContext(), "Please enter Zipcode in Profile!", Toast.LENGTH_SHORT);
                            successToast.show();
                            return;
                        }
                        try {
                            URL url = new URL("https://apis.berkeley.edu:443/coolclimate/footprint-defaults?input_location=" + zipcode + "&input_income=1&input_location_mode=1&input_size=0");

                            // Create the request to OpenWeatherMap, and open the connection
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("GET");
                            // String userCredentials = "username:password";
                            //String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
                            urlConnection.setRequestProperty("App_id", "1c59b0f9");
                            urlConnection.setRequestProperty("App_key", "d85b07f8673cb00694d6eab51fe3dd16");
                            // urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            //urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
                            //urlConnection.setRequestProperty("Content-Language", "en-US");
                            urlConnection.setUseCaches(false);
                            urlConnection.setDoInput(true);
                            urlConnection.setDoOutput(true);
                            urlConnection.connect();

                            // Read the input stream into a String
                            InputStream inputStream = urlConnection.getInputStream();
                            StringBuffer buffer = new StringBuffer();
                            if (inputStream == null) {
                                // Nothing to do.
                                return;
                            }
                            reader = new BufferedReader(new InputStreamReader(inputStream));

                            String line;
                            while ((line = reader.readLine()) != null) {
                                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                                // But it does make debugging a *lot* easier if you print out the completed
                                // buffer for debugging.
                                buffer.append(line + "\n");
                            }

                            if (buffer.length() == 0) {
                                // Stream was empty.  No point in parsing.
                                return;
                            }
                            coolClimateAPIJsonStr = buffer.toString();
                        } catch (IOException e) {
                            Log.e("PlaceholderFragment", "Error ", e);
                            // If the code didn't successfully get the weather data, there's no point in attemping
                            // to parse it.
                            return;
                        } finally {
                            if (urlConnection != null) {
                                urlConnection.disconnect();
                            }
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (final IOException e) {
                                    Log.e("PlaceholderFragment", "Error closing stream", e);
                                }
                            }
                        }
                    }*/
                }

                public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                    if (requestCode == 0) {
                        if (resultCode == RESULT_OK) {
                            String contents = intent.getStringExtra("SCAN_RESULT");
                            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                            // Handle successful scan
                            Toast myToast = new Toast(getActivity());
                            myToast.setText("contents:" + contents + "|format:" + format);
                            myToast.show();
                        } else if (resultCode == RESULT_CANCELED) {
                            // Handle cancel
                        }
                    }
                };
            });

            return rootView;
        }
    }
}
