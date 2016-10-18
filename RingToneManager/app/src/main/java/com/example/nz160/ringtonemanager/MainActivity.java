package com.example.nz160.ringtonemanager;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    Button btnStart, btnStop;
    Ringtone ringTone;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private NotificationManager mNotificationManager;
    private Calendar calNow;
    private Calendar calSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));

        // setting notification manager
        int[] alarm_min={44,45,46};
        for(int i=0;i<alarm_min.length;i++) {
            calNow = Calendar.getInstance();
            calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.YEAR, 2016);
            calSet.set(Calendar.MONTH, 9);
            calSet.set(Calendar.DAY_OF_MONTH, 18);
            calSet.set(Calendar.HOUR_OF_DAY, 5);
            calSet.set(Calendar.MINUTE, alarm_min[i]);
            calSet.set(Calendar.MILLISECOND, 0);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.AM_PM, Calendar.PM);
            if(calSet.compareTo(calNow) <= 0){
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }
            RegisterAlarmBroadcast(i);
        }

    }

    private void RegisterAlarmBroadcast(int i)
    {
        Intent intent = new Intent(this, AlarmReceiverActivity.class);

        PendingIntent sender = PendingIntent.getBroadcast(this, i, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), sender);
    }
}
