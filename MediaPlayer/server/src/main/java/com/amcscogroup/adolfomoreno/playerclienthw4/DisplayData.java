package com.amcscogroup.adolfomoreno.playerclienthw4;

// Author: Adolfo Moreno
// Due Date: April 9th
// Assignment: Project 4
// Class: CS 478
import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
//import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

// Activity that will display the transaction data to show the query on the client side.
public class DisplayData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        // Retrieve extras from the intent (Transactions)
        Bundle extras = getIntent().getExtras();
        String hey = extras.getString("string");

        // Display the transactions in a textview
        ((TextView)findViewById(R.id.textView2)).setText(hey);


        //setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_display_data, hey));
    }
}
