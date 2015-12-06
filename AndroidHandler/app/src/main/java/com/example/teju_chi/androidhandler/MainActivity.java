package com.example.teju_chi.androidhandler;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

    android.os.Handler handler;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        Thread thread=new Thread(new MyThread());
        thread.start();
        handler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
            }
        };
    }


   class MyThread implements Runnable{

      @Override
      public void run() {
          for(int i=0;i<100;i++){
              Message message=Message.obtain();

              message.arg1=i;
              handler.sendMessage(message);
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
}
