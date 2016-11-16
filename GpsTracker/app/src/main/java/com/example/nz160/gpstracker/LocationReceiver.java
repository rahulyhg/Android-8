package com.example.nz160.gpstracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nz160 on 16-11-2016.
 */
public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String address=intent.getStringExtra("address");
        System.out.println("Addressss Broadcast reveiver "+address);
    }
}
