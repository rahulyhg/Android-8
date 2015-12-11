package com.example.teju_chi.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Teju-Chi on 12/11/2015.
 */
public class MyBoundService extends Service {

    private final IBinder mbinder=new LocalMyservice();
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    class LocalMyservice extends Binder{

        MyBoundService getService(){
            return MyBoundService.this;
        }

    }

    public String getFirstMessage(){
        return "Hellooo";
    }

    public String getSecondtMessage(){
        return "Hellooo Hiiiii";
    }
}
