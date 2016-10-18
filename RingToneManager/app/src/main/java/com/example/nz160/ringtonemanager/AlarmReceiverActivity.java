package com.example.nz160.ringtonemanager;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by nz160 on 17-10-2016.
 */
public class AlarmReceiverActivity extends BroadcastReceiver {
    private MediaPlayer mMediaPlayer;
    private NotificationManager mNotificationManager;
    private Context context;
    private int numMessages_SingleLine = 0;
    private final int notificationID_SingleLine = 111;
    private final long[] pattern = { 100, 300, 300, 300 };// Vibrate pattern in
    private Uri alarmSound;

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

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        Toast.makeText(context, "Congrats!. Your Alarm time has been reached", Toast.LENGTH_LONG).show();
       /* Intent i = new Intent();
        i.setClassName("com.example.nz160.ringtonemanager", "com.example.nz160.ringtonemanager.Alarm");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
        mNotificationManager = (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
        alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Dialog dialog = new Dialog(context.getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alarm);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.show();
        //playSound(context,getAlarmUri());

        Button cancel=(Button)dialog.findViewById(R.id.cancel_alarm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
            }
        });
        displayNotificationSingleLine();
    }
    /** Single Line Notifications Methods **/
    protected void displayNotificationSingleLine() {
        Log.i("Start", "notification");
		/* Invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
               context);// Setting builder
        mBuilder.setContentTitle("New Message");// title
        mBuilder.setContentText("You've received new message from Androhub.");// Message
        mBuilder.setTicker("New Message Alert!");// Ticker
        mBuilder.setSmallIcon(R.drawable.alarm);// Small icon
		/* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages_SingleLine);

        mBuilder.setSound(alarmSound);// set alarm sound
        mBuilder.setVibrate(pattern);// set vibration
		/* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(context,
                NotificationActivity.class);

        resultIntent.putExtra("notificationId", notificationID_SingleLine);// put
        // notification
        // id
        // into
        // intent
        resultIntent.putExtra("message", "http://androhub.com");//Your message to show in next activity

        // Task builder to maintain task for pending intent
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
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