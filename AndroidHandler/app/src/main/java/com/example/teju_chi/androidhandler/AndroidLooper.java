package com.example.teju_chi.androidhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

/**
 * Created by Teju-Chi on 12/6/2015.
 */
public class AndroidLooper extends Activity{

    private MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        thread= new MyThread();
        thread.start();
    }

    public void buttonClicked(View view){
        thread.handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Thread name",Thread.currentThread().getName());
            }
        });
    }

    class MyThread extends Thread {

        Handler handler;

        @Override
        public void run() {
            //Puts message to thread msg queue
            Looper.prepare();
            handler=new Handler();
            //Loops over msgs of msg queue
            Looper.loop();
            
        }
    }
}
