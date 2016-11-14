package com.example.nz160.broadcastreceivertestwithapilevel;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar calSet;
    private AlarmManager alarmManager;
    public final static int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) (MainActivity.this.getSystemService(Context.ALARM_SERVICE));
        if(Build.VERSION.SDK_INT >= 23) {
            Toast.makeText(getApplicationContext(),"checkDrawOverlayPermission",Toast.LENGTH_LONG).show();
            checkDrawOverlayPermission();
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void checkDrawOverlayPermission() {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
        } else {

            Alarm();
        }
    }
    public void Alarm(){
        Calendar calNow = null;
        int[] min={31,07,00,5};
        int[] hr={12,12,10,10};
        int[] day={10,10,11,11};
        for (int i = 0; i < min.length; i++) {
                        /*System.out.println("PRINTINGSCHEDULEINFO Date :" + schedule.get(i).getDate() +
                                " month :" + schedule.get(i).getSchedule_month() + " year :" + schedule.get(i).getYear() +
                                " hour:" + schedule.get(i).getSchedule_hour() + " AMPM :" +
                                schedule.get(i).getSchedule_AM_PM() + " " + schedule.size());*/
            calNow = Calendar.getInstance();
            calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.YEAR, 2016);
            calSet.set(Calendar.MONTH, 10);
            calSet.set(Calendar.DAY_OF_MONTH,day[i] );
            calSet.set(Calendar.HOUR_OF_DAY, hr[i]);
            calSet.set(Calendar.MINUTE, min[i]);
            calSet.set(Calendar.MILLISECOND, 0);
            calSet.set(Calendar.SECOND, 0);
            //  if (schedule.get(i).getSchedule_AM_PM().equals("PM")) {
            calSet.set(Calendar.AM_PM, Calendar.PM);
            //} else {
            //     calSet.set(Calendar.AM_PM, Calendar.AM);
            // }
            if (calSet.compareTo(calNow) < 1) {
                UnregisterAlarmBroadCast(i);
            } else {
                Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
                RegisterAlarmBroadcast(i);
            }
        }
        System.out.println("PRINTINGTIMEFORVERIFICATIOn " + calSet +
                " " + calNow + calSet.compareTo(calNow));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void RegisterAlarmBroadcast(int i) {

        Intent intent = new Intent(this, AlarmReceiverActivity.class);

        PendingIntent sender = PendingIntent.getBroadcast(this, i, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), sender);
    }


    private void UnregisterAlarmBroadCast(int i)
    {
        System.out.println("PRINTIMGVALUEODIUNREG "+i );
        AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), AlarmReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        aManager.cancel(pIntent);
    }

}
