package com.example.teju_chi.androidservices;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startService(View view){

        Intent i=new Intent(this,MyStartedService.class);
        startService(i);
    }

    public void stopService(View view){
        Intent i=new Intent(this,MyStartedService.class);
        stopService(i);
    }

    public void boundService(View view){
        Intent i=new Intent(this,MainActivityBound.class);
        startActivity(i);
    }
}
