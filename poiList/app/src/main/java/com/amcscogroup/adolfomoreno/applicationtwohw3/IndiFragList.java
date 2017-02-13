package com.amcscogroup.adolfomoreno.applicationtwohw3;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Author: Adolfo Moreno
// Net-id: asmoren2
// Class:  CS 478 - Mobile App Dev.

// Class Description:
//      This class is used to handle the List Fragment and setting up the list of POI's for Indianapolis.

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndiFragList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class IndiFragList extends ListFragment{

    private ListSelectionListener nListener = null; // Create a listener so that we can check what we clicked on
    private static final String TAG = "IndiListFragment";


    public IndiFragList() {
        // Required empty public constructor
    }

    // Callback interface that allows this Fragment to notify the indiPOI Activity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(container!=null)
        {
            container.removeAllViews();
        }
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {

            // Set the ListSelectionListener for communicating with the QuoteViewerActivity
            nListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Get List of POI's
        String [] test = getResources().getStringArray(R.array.IndianapolisPOI);

        // Populate the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_indi_frag_list, test));

    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        // Important: Must have setChoiceMode to either CHOICE_MODE_SINGLE or MULTIPLE
        getListView().setItemChecked(pos, true);

        // Inform the QuoteViewerActivity that the item in position pos has been selected
        nListener.onListSelection(pos);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
