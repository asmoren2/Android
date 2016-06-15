package com.amcscogroup.adolfomoreno.playerclienthw4;

// Author: Adolfo Moreno
// Due Date: April 9th
// Assignment: Project 4
// Class: CS 478
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.amcscogroup.adolfomoreno.mediacommon.MyMediaInterface;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

// Client interaface for the media player. Interacts with Audioserver to play the audio.
// Supporst Play, Pause, Resume, Stop, And Get Transactions.
public class MainActivity extends ListActivity {

    private MyMediaInterface mMusicService; // Create an instance of our AIDL to interact with server
    protected static final String TAG = "KeyServiceUser"; // Used for logcat output
    boolean mServiceBound = false; // Check to see if service is bound
    private String[] songNameList = {"1. Bad News", "2. A New Beginning", "3. Better Days", "4. Clear Day", "5. Happy Rock"};
    //{"1. Bad News", "2. Or Nah", "3. Often", "4. Earned It", "5. In The Night", "6. The Hills" ,"7. Acquainted", "8. Wonderful", "9. Be Together", "10. All My Love"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Populate our initial list view with possible audio choices for our media player.
        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.musiclist, songNameList));

        // Allows play of the first song in the list (#0)
        final Button startButton = (Button) findViewById(R.id.playbutton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                try {

                    // Check if service is bound if yes procceed.
                    if (mServiceBound)
                        mMusicService.play(); // Play the current song
                    else {
                        Log.i(TAG, "service was not bound!");
                    }

                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());

                }
            }
        });

        // Pause the curent song
        final Button pauseButton = (Button) findViewById(R.id.pausebutton); // Pause button
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                //Toast.makeText(getApplicationContext(), "Pressed pause", Toast.LENGTH_SHORT).show();
                try {

                    // Check if service is bound if yes procceed.
                    if (mServiceBound)
                        mMusicService.pause(); // Pause the current song
                    else {
                        Log.i(TAG, "service was not bound!");
                    }

                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());

                }
            }
        });

        // Resume the current song that was paused
        final Button resumeButton = (Button) findViewById(R.id.resumebutton); // Resume Button
        resumeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                //Toast.makeText(getApplicationContext(), "Pressed Resume", Toast.LENGTH_SHORT).show();
                try {

                    // Check if service is bound if yes procceed.
                    if (mServiceBound)
                        mMusicService.resume(); // Resume the current song
                    else {
                        Log.i(TAG, "service was not bound!");
                    }

                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());

                }
            }
        });

        // Stop the current song being played
        final Button stopButton = (Button) findViewById(R.id.stopbutton); // Stop Button
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {

                try {
                    // if the service is bound procceed with stopping the song
                    if (mServiceBound)
                        mMusicService.stop(); // stop the current song
                    else {
                        Log.i(TAG, "Service was not bound!");
                    }

                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());

                }
            }
        });

        // Display the list of transactions through a new activity
        final Button getButton = (Button) findViewById(R.id.getbutton); // Display button
        getButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {
                // intent for new activity
                Intent intent = new Intent(getApplicationContext(), DisplayData.class);
                try {
                    // Check if the service is bound if true procceed to display transactions
                    if (mServiceBound)
                        intent.putExtra("string", mMusicService.parseCursor());// Store transactions
                    else {
                        Log.i(TAG, "Service was not bound!");
                    }

                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());

                }
                startActivity(intent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // Bind to MusicPlayer Service
    @Override
    protected void onResume() {
        super.onResume();


        if (!mServiceBound) {

            boolean b = false;
            Intent i = new Intent(MyMediaInterface.class.getName());

            // Explicit intent needed
            ResolveInfo info = getPackageManager().resolveService(i, Context.BIND_AUTO_CREATE);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

            b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Log.i(TAG, "bindService() succeeded!");
            } else {
                Log.i(TAG, "bindService() failed!");
            }

        }
    }

//    @Override
//    protected void onPause() {
//        if (mServiceBound) {
//            unbindService(this.mConnection);
//        }
//        super.onPause();
//    }

    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder iservice) {

            mMusicService = MyMediaInterface.Stub.asInterface(iservice);

            mServiceBound = true;

        }

        public void onServiceDisconnected(ComponentName className) {

            mMusicService = null;

            mServiceBound = false;

        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.amcscogroup.adolfomoreno.playerclienthw4/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.amcscogroup.adolfomoreno.playerclienthw4/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // Used to retrieve song that user wants to play from that list
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        // Important: Must have setChoiceMode to either CHOICE_MODE_SINGLE or MULTIPLE
        //getListView().setItemChecked(pos, true);
        try {

            // Check if bound if true procceed to play that song
            if (mServiceBound)
                mMusicService.playSong(pos); // Play the song at position selected
            else {
                Log.i(TAG, "Service was not bound!");
            }

        } catch (RemoteException e) {

            Log.e(TAG, e.toString());

        }


    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent = new Intent(this, MyMediaInterface.class);
//        startService(intent);
//        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mServiceBound) {
//            unbindService(mServiceConnection);
//            mServiceBound = false;
//        }
//    }


}

