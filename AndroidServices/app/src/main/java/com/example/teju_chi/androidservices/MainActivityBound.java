package com.example.teju_chi.androidservices;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Teju-Chi on 12/11/2015.
 */
public class MainActivityBound extends Activity {

    TextView textView;
    MyBoundService myBoundService;
    boolean isBound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        Intent i=new Intent(this,MyBoundService.class);
        bindService(i,serviceConnection,BIND_AUTO_CREATE);
        textView=(TextView)findViewById(R.id.textView2);

    }

    public void getFirstMessage(View view){
        textView.setText(myBoundService.getFirstMessage());

    }

    public void getSecondMessage(View view){
        textView.setText(myBoundService.getSecondtMessage());

    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalMyservice localMyservice= (MyBoundService.LocalMyservice) service;
            myBoundService=localMyservice.getService();
            isBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(serviceConnection);
            isBound=false;
        }
    }
}
