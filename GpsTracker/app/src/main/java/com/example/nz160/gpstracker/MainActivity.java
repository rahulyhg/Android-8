package com.example.nz160.gpstracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private double latitude=0;
    private double longitude=0;
    public String address;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitudeLongitude();
        Geocoder code = new Geocoder(MainActivity.this);
        StringBuilder sb1 = new StringBuilder();

        try {
            List<Address> addresses = code.getFromLocation(latitude, longitude, 1);
            Address add = new Address(Locale.getDefault());
            if (addresses.size() > 0) {
                add = addresses.get(0);
                
                for (int i = 0; i < add.getMaxAddressLineIndex(); i++) {
                    sb1 = sb1.append(add.getAddressLine(i)).append(", ");
                }
                 address = sb1.toString();
            }
            System.out.println("MY Current Address "+address);
            Intent alarmIntent = new Intent(MainActivity.this, LocationReceiver.class);
            alarmIntent.putExtra("address", address);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            setAlarm();
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
    }

    //Function to get latitude of current location
    public void latitudeLongitude() {
        AppLocationService appLocationService = new AppLocationService(
                MainActivity.this);

        Location location = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);
        try {
            if (!location.equals(null)) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        getApplicationContext(), new GeocoderHandler());
            } else {
                showSettingsAlert();
            }
            System.out.println("LAtitude "+latitude+" "+longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class GeocoderHandler extends Handler {


        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address = "null";
            }
        }
    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    // set alarm to sync the data from sqlite to server for every 1 min
    public void setAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 6000;
        Log.d("alaraaaaaam", pendingIntent.toString());
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

}
