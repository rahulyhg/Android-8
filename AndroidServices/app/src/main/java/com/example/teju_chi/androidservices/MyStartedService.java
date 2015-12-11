package com.example.teju_chi.androidservices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Teju-Chi on 12/11/2015.
 */
public class MyStartedService extends Service {
    private MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        mp=new MediaPlayer();
        String fd=(Environment.getExternalStorageDirectory().getPath()+"/song.mp3");
        System.out.println("String path "+fd);
        try {
            mp.setDataSource(fd);
            mp.prepare();
            mp.start();
            Toast.makeText(this,"Song Started",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.d("Exception occured",e.toString());
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
