package com.example.bhavana.energysaverapp;

import android.app.ExpandableListActivity;
import android.content.Context;
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
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SolutionsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solutions, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_solutions, container, false);

            ExpandableListView expandbleLis = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view);

            SimpleExpandableListAdapter expListAdapter =
                    new SimpleExpandableListAdapter(
                            rootView.getContext(),
                            setGroupData(),              // Creating group List.
                            R.layout.group_row,             // Group item layout XML.
                            new String[] { "Group Item" },  // the key of group item.
                            new int[] { R.id.row_name },    // ID of each group item.-Data under the key goes into this TextView.
                            setChildGroupData(),              // childData describes second-level entries.
                            R.layout.child_row,             // Layout for sub-level entries(second level).
                            new String[] {"Child Item"},      // Keys in childData maps to display.
                            new int[] { R.id.grp_child}     // Data under the keys above go into these TextViews.
                    );
            expandbleLis.setAdapter(expListAdapter);
            //setListAdapter(expListAdapter);       // setting the adapter in the list.

            return rootView;
        }
    }
    @SuppressWarnings("unchecked")
    public static List setGroupData() {
        ArrayList result = new ArrayList();
        HashMap m = new HashMap();
        m.put("Group Item", "BASIC SOLUTION");
        result.add( m );
        m = new HashMap();
        m.put("Group Item", "SECONDARY SOLUTION");
        result.add( m );
        m = new HashMap();
        m.put( "Group Item","LIST OF ENERGY PROVIDERS FOR YOU");
        result.add( m );
        return (List)result;
    }



    public static List setChildGroupData() {
        ArrayList result = new ArrayList();
        ArrayList secList = new ArrayList();
            HashMap m = new HashMap();
            m.put("Child Item", "Reduce Heater runtime/usage.");
            secList.add( m );
            m = new HashMap();
            m.put("Child Item", "Reduce Television runtime/usage.");
            secList.add( m );
        result.add(secList);

        secList = new ArrayList();
            m = new HashMap();
            m.put("Child Item", "Switch to ABC Heater (Model No. 971) by ABC Enterprise.");
            secList.add( m );
            m = new HashMap();
            m.put("Child Item", "Switch to XYZ Television (Model No. 374) by XYZ Enterprise");
            secList.add( m );
        result.add(secList);

        secList = new ArrayList();
            m = new HashMap();
            m.put( "Child Item","Star Energy : 0.30/watt");
            secList.add( m );
            m = new HashMap();
            m.put( "Child Item","Asterix Supply : 0.25- 0.40/watt (variable cost)");
            secList.add( m );
            m = new HashMap();
            m.put( "Child Item","Matt Energy : 0.20-0.60/watt (variable cost)");
            secList.add( m );
        result.add(secList);
        return (List)result;
    }
}
