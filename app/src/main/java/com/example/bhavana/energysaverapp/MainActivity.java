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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends ActionBarActivity {


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
                    "Add Bill",
                    "Scan Bar Code",
                    "Get Bill from Site"
            };
            ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_layout,
                    R.id.list_item_menu_item,
                    mainMenuItems);

            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView myListView = (ListView) rootView.findViewById(R.id.listView_menu);
            myListView.setAdapter(myArrayAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Redirect to BarCode Scanner App
                    if(mainMenuItems[2].equalsIgnoreCase((String) ((TextView) view).getText())) {
                        Log.i("Event Listener",view.toString());
                        IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
                        scanIntegrator.initiateScan();
                    }
                    // Redirect to a website
                    else if(mainMenuItems[3].equalsIgnoreCase((String) ((TextView) view).getText()))
                    {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                        startActivity(i);
                    }
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
