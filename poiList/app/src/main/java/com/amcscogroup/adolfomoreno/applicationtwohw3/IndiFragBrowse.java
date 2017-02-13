package com.amcscogroup.adolfomoreno.applicationtwohw3;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


// Author: Adolfo Moreno
// Net-id: asmoren2
// Class:  CS 478 - Mobile App Dev.

// Class Description:
//      This class is used to handle the Browser Fragment and setting up the webview element with
//      the correct url depending on item clicked.

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndiFragBrowse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class IndiFragBrowse extends Fragment {

    private static final String TAG = "BrowseFragment";

    private WebView mBrowseView = null;
    private int mCurrIdx = -1;          // Current Index
    private int mURLArrayLen;           // Length of array

    public IndiFragBrowse() {
        // Required empty public constructor
    }

    // Get the index clicked on
    int getShownIndex() {
        return mCurrIdx;
    }


    // Open URL at position newIndex
    void openURLAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mURLArrayLen)
            return;
        mCurrIdx = newIndex;

        WebView webview = (WebView) getView().findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(IndiPOI.mIndiBrowseArray[mCurrIdx]);

    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(context);
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

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indi_frag_browse, container, false);
    }

    // Get the length of the array and the webview
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");

        // Get the length of the array and the webview
        mBrowseView = (WebView) getActivity().findViewById(R.id.webView);
        mURLArrayLen = IndiPOI.mIndiBrowseArray.length;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // Needed to have a webClient to open up url
    class webClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
            //return false;

        }

    }
}

