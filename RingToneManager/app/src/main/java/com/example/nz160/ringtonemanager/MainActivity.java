package com.example.nz160.ringtonemanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    Button btnStart, btnStop;
    Ringtone ringTone;

    Uri uriAlarm, uriNotification, uriRingtone;
    private BroadcastReceiver mReceiver;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private MediaPlayer mMediaPlayer;
    private Uri alarmSound;
    private int numMessages_SingleLine = 0;
    private final int notificationID_SingleLine = 111;
    private final long[] pattern = { 100, 300, 300, 300 };// Vibrate pattern in

    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set by default alarm sound
        alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // setting notification manager
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        int[] alarm_min={03,04,05};
        //Get the current time and set alarm after 10 seconds from current time
        // so here we get
        for(int i=0;i<alarm_min.length;i++) {
            //RegisterAlarmBroadcast();
            Calendar calNow = Calendar.getInstance();
           // Calendar calSet = (Calendar) calNow.clone();
            calNow.set(Calendar.YEAR, 2016);
            calNow.set(Calendar.MONTH, 9);
            calNow.set(Calendar.DAY_OF_MONTH, 17);
            calNow.set(Calendar.HOUR_OF_DAY, 6);
            calNow.set(Calendar.MINUTE, 16);
            calNow.set(Calendar.MILLISECOND, 0);
            calNow.set(Calendar.SECOND, 0);
            calNow.set(Calendar.AM_PM, Calendar.PM);

            RegisterAlarmBroadcast(i);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calNow.getTimeInMillis(), pendingIntent);
        }
    }

    private void RegisterAlarmBroadcast(int i)
    {
        Log.i("Alarm Example:RegisterAlarmBroadcast()", "Going to register Intent.RegisterAlramBroadcast");

        //This is the call back function(BroadcastReceiver) which will be call when your
        //alarm time will reached.
        mReceiver = new BroadcastReceiver()
        {
            private static final String TAG = "Alarm Example Receiver";
            @Override
            public void onReceive(Context context, Intent intent)
            {
                Log.i(TAG, "BroadcastReceiver::OnReceive() >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                Toast.makeText(context, "Congrats!. Your Alarm time has been reached", Toast.LENGTH_LONG).show();
                Intent resultIntent = new Intent(MainActivity.this,
                        NotificationActivity.class);

                resultIntent.putExtra("notificationId", notificationID_SingleLine);// put
                // notification
                // id
                // into
                // intent
                resultIntent.putExtra("message", "http://androhub.com");//Your message to show in next activity

                // Task builder to maintain task for pending intent
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(NotificationActivity.class);
		/* Adds the Intent that starts the Activity to the top of the stack */
                stackBuilder.addNextIntent(resultIntent);

                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);// Set flag to update current
                //mBuilder.setContentIntent(resultPendingIntent);// set content intent
                displayNotificationSingleLine();
                //playSound(MainActivity.this, getAlarmUri());

            }
        };

        // register the alarm broadcast here
        registerReceiver(mReceiver, new IntentFilter("com.techblogon.alarmexample") );
        pendingIntent = PendingIntent.getBroadcast( this, i, new Intent("com.techblogon.alarmexample"),0 );
        alarmManager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
    }
    public void StopAlarm(View v)
    {
        mMediaPlayer.stop();
        finish();
    }

    private void playSound(Context context, Uri alert) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            System.out.println("OOPS");
        }
    }

    //Get an alarm sound. Try for an alarm. If none set, try notification,
    //Otherwise, ringtone.
    private Uri getAlarmUri() {
        Uri alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert == null) {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }
    /** Single Line Notifications Methods **/
    protected void displayNotificationSingleLine() {
        Log.i("Start", "notification");
		/* Invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                MainActivity.this);// Setting builder
        mBuilder.setContentTitle("New Message");// title
        mBuilder.setContentText("You've received new message from Androhub.");// Message
        mBuilder.setTicker("New Message Alert!");// Ticker
        mBuilder.setSmallIcon(R.drawable.alarm);// Small icon
		/* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages_SingleLine);

        mBuilder.setSound(alarmSound);// set alarm sound
        mBuilder.setVibrate(pattern);// set vibration
		/* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(MainActivity.this,
                NotificationActivity.class);

        resultIntent.putExtra("notificationId", notificationID_SingleLine);// put
        // notification
        // id
        // into
        // intent
        resultIntent.putExtra("message", "http://androhub.com");//Your message to show in next activity

        // Task builder to maintain task for pending intent
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
		/* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);// Set flag to update current
        mBuilder.setContentIntent(resultPendingIntent);// set content intent

		/* notificationID allows you to update the notification later on. */
        mNotificationManager
                .notify(notificationID_SingleLine, mBuilder.build());
    }
}
