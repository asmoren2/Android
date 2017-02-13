package com.amcscogroup.adolfomoreno.applicationtwohw3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

// Author: Adolfo Moreno
// Net-id: asmoren2
// Class:  CS 478 - Mobile App Dev.

// Class Description:
//      This class is used to handle everything happening when the user selects Indianapolis in App 1.
//      This class populates the arrays with the string found in strings.xml then sets the Array
//      Adapter with our list of POI's. Also creates the Browser fragment

public class IndiPOI extends Activity implements IndiFragList.ListSelectionListener {
    public static String[] mIndiPOIArray;           // Hold the names of the POI's
    public static String[] mIndiBrowseArray;        // Hols the POI url's

    // Get a reference to the BrowseFragment
    private IndiFragBrowse BrowseFragment = new IndiFragBrowse();   // Create our Browser Fragment
    private FragmentManager fragmentManager;                        // Declare the fragment manager
    private FrameLayout mListFrameLayout, mBrowseLayout;            // FrameLayout to be set
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    private static final String TAG = "IndiActivity";

    // Initiates fragments and begins transactions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndiPOIArray = getResources().getStringArray(R.array.IndianapolisPOI);
        mIndiBrowseArray = getResources().getStringArray(R.array.IndianaPolisUrls);

        setContentView(R.layout.activity_indi_poi);

        mBrowseLayout = (FrameLayout) findViewById(R.id.in_browse_frame);
        mListFrameLayout = (FrameLayout) findViewById(R.id.in_list_frame);


        // Get a reference to the FragmentManager
        fragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the ListFragment to the layout
        fragmentTransaction.add(R.id.in_list_frame, new IndiFragList());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        fragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }

    // Depending on the orientation set the layout. Self Explanatory.
    private void setLayout(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            if (!BrowseFragment.isAdded()) {

                // Make the ListFragment occupy the entire layout
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the ListLayout take 1/3 of the layout's width
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0f));

                // Make the BrowseLayout take 2/3's of the layout's width
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if (!BrowseFragment.isAdded()) {

                // Make the ListFragment occupy the entire layout
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the ListLayout take 1/3 of the layout's width
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the BrowseLayout take 2/3's of the layout's width
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }

    }

    // Depending on the orientation set the layout. Self Explanatory.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();

            if (!BrowseFragment.isAdded()) {

                // Make the TitleFragment occupy the entire layout
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the TitleLayout take 1/3 of the layout's width
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            if (!BrowseFragment.isAdded()) {

                // Make the TitleFragment occupy the entire layout
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the TitleLayout take 1/3 of the layout's width
                mListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0));

                // Make the BrowseLayout take 2/3's of the layout's width
                mBrowseLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, MATCH_PARENT));
            }
        }
    }

    // Inflate the options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // Set an action to each option in the menu
    // Disable Chicago since we are already there
    // Send intent to display Indianapolis Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.background:
                changeBackground();
                return true;
            case R.id.toast:
                Toast.makeText(getBaseContext(), "Already in Indianapolis",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Set new intents for Indianapolis ACTIVITY
    private void changeBackground() {
        Intent intent = new Intent(this, ChiPOI.class);
        this.startActivity(intent);
    }

    // Called when the user selects an item in the ListFragment
    @Override
    public void onListSelection(int index) {

        // If the BrowserFragment has not been added, add it now
        if (!BrowseFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.in_browse_frame,
                    BrowseFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            fragmentManager.executePendingTransactions();
        }

        if (BrowseFragment.getShownIndex() != index) {

            // Tell the Browse Fragment to open the url at position index
            BrowseFragment.openURLAtIndex(index);
        }
    }
}
