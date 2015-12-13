package com.example.teju_chi.androidbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Teju-Chi on 12/13/2015.
 */
public class StaticBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"StaticBroadCastReceiver",Toast.LENGTH_LONG).show();
    }
}
