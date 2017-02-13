package com.amcscogroup.adolfomoreno.applicationtwohw3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Author: Adolfo Moreno
// Net-id: asmoren2
// Class:  CS 478 - Mobile App Dev.


// MainActivity serves as the receiver for the Broadcast Signal sent by Application 1
// The onReceive Checks the extras put into the intent in this case a String which allows
// us to initiate the correct Activity.

public class MainActivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String newString;
        newString = intent.getStringExtra("sendReceipt");
        System.out.println(newString);

        if(newString.equals("Chicago")){
            Intent i = new Intent(context, ChiPOI.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else{
            Intent i = new Intent(context, IndiPOI.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}
