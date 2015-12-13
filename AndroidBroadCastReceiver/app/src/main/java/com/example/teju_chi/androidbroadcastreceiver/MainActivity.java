package com.example.teju_chi.androidbroadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {


     String Custom_intent="com.example.teju_chi.androidbroadcastreceiver.receive_toast";
     DynamicBroadCastReceiver dynamicBroadCastReceiver=new DynamicBroadCastReceiver();
     IntentFilter intentFilter=new IntentFilter(Custom_intent);
     LocalBroadcastManager localBroadcastManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(getApplicationContext());
        localBroadcastManager.registerReceiver(dynamicBroadCastReceiver,intentFilter);

    }

    public void staticBroadcastReceiver(View view){
        sendBroadcast(new Intent((Custom_intent)));

    }

    public void dynamicBroadcastReceiver(View view){
        localBroadcastManager.sendBroadcast(new Intent(Custom_intent));

    }


}
