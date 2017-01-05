package com.example.nz160.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "61iMpvg7BhnQeXquUbkGQb76H";
    private static final String TWITTER_SECRET = "ffjbP3FU092AZL4uzUQ5rbQC3A2AD0jeWTjWOV5fRE3boocDl4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_main);
    }
    public void digitalLogin(View view){
        Intent i=new Intent(this,DigitalLogin.class);
        startActivity(i);
    }
}
