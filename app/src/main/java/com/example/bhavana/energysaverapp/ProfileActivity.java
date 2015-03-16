package com.example.bhavana.energysaverapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bhavana.energysaverapp.Model.Profile;

import static android.widget.Toast.LENGTH_SHORT;


public class ProfileActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

        EditText editTextName;
        EditText editTextNoOfDevices;
        EditText editTextPhoneNo;
        EditText editTextPostalCode;
        EditText editTextProviderRate;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

            Button saveButton = (Button) rootView.findViewById(R.id.saveButton);
            editTextName = ((EditText) (rootView.findViewById(R.id.editTextName)));
            editTextNoOfDevices = ((EditText) (rootView.findViewById(R.id.editTextNoOfDevices)));
            editTextPhoneNo = ((EditText) (rootView.findViewById(R.id.editTextPhoneNo)));
            editTextPostalCode = ((EditText) (rootView.findViewById(R.id.editTextPostalCode)));
            editTextProviderRate = ((EditText) (rootView.findViewById(R.id.editTextProviderRate)));
            saveButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.profile = new Profile();

                    MainActivity.profile.setName(editTextName.getText().toString());
                    MainActivity.profile.setNumberOfDevices(Integer.valueOf(editTextNoOfDevices.getText().toString()));
                    MainActivity.profile.setPhoneNo(Integer.valueOf(editTextPhoneNo.getText().toString()));
                    MainActivity.profile.setPostalCode(editTextPostalCode.getText().toString());
                    MainActivity.profile.setProviderRate(Float.valueOf(editTextProviderRate.getText().toString()));

                    Toast successToast = Toast.makeText(v.getContext(), "Profile Save Successfully!", LENGTH_SHORT);
                    successToast.show();

                    // TODO: Fix Move back to Main Activity
                    if (getActivity().findViewById(R.id.container) != null){
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.popBackStackImmediate();
                    }
                    else{getActivity().finish();}

                }
            });

            return rootView;
        }
    }
}
