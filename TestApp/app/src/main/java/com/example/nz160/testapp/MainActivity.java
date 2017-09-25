package com.example.nz160.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

private static final int RC_SIGN_IN = 0;
// Logcat tag
private static final String TAG = "MainActivity";

// Profile pic image size in pixels
private static final int PROFILE_PIC_SIZE = 400;

// Google client to interact with Google API
private GoogleApiClient mGoogleApiClient;

/**
 * A flag indicating that a PendingIntent is in progress and prevents us
 * from starting further intents.
 */
private boolean mIntentInProgress;

private boolean mSignInClicked;

private ConnectionResult mConnectionResult;

private Button btnSignIn;
private Button btnSignOut, btnRevokeAccess;
private ImageView imgProfilePic;
private TextView txtName, txtEmail;
private LinearLayout llProfileLayout;

@Override
protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button)findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button)findViewById(R.id.btn_revoke_access);
        imgProfilePic = (ImageView)findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        llProfileLayout = (LinearLayout)findViewById(R.id.llProfile);
        System.out.println("CHECKINGFLOW " + " onCreate");

        // Button click listeners
        btnSignIn.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build())
        .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        }



public void onStop() {
        System.out.println("CHECKINGFLOW "+" onStop");

        super.onStop();
        if (mGoogleApiClient.isConnected()) {
        mGoogleApiClient.disconnect();
        }
        }

/**
 * Method to resolve any signin errors
 * */
private void resolveSignInError() {
        System.out.println("CHECKINGFLOW "+" resolveSignInError");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build())
        .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        //System.out.println(" resolveSignInError "+mConnectionResult.hasResolution());
        try {
        if (mConnectionResult.hasResolution()) {
        try {
        mIntentInProgress = true;
        mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
        } catch (Exception e) {
        mIntentInProgress = false;
        mGoogleApiClient.connect();
        }
        }
        } catch (Exception e){
        mIntentInProgress = false;
        mGoogleApiClient.connect();
        }
        }

@Override
public void onConnectionFailed(ConnectionResult result) {
        System.out.println("CHECKINGFLOW "+" onConnectionFailed");

        if (!result.hasResolution()) {
        System.out.println("CHECKINGFLOW "+" onConnectionFailed "+" result.hasResolution");

        GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), getParent(),
                0).show();
        System.out.println("CHECKINGFLOW " + " onConnectionFailed " + " GooglePlayServicesUtil");

        return;
        }

        if (!mIntentInProgress) {
        // Store the ConnectionResult for later usage
        mConnectionResult = result;
        System.out.println("CHECKINGFLOW " + " onConnectionFailed " + " !mIntentInProgress");

        if (mSignInClicked) {
        // The user has already clicked 'sign-in' so we attempt to
        // resolve all
        // errors until the user is signed in, or they cancel.
        System.out.println("CHECKINGFLOW " + " onConnectionFailed " + " mSignInClicked");

        resolveSignInError();
        }
        }
        }

@Override
protected void onActivityResult(int requestCode, int responseCode,
        Intent intent) {
        System.out.println("CHECKINGFLOW " + " onActivityResult " + requestCode + " " + responseCode);

        if (requestCode == RC_SIGN_IN) {
        if (responseCode != RESULT_OK) {
        mSignInClicked = false;
        }

        mIntentInProgress = false;

        if (!mGoogleApiClient.isConnecting()) {
        mGoogleApiClient.connect();
        }
        }
        }

@Override
public void onConnected(Bundle arg0) {
        System.out.println("CHECKINGFLOW "+" onConnected");

        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        // Get user's information
        getProfileInformation();

        // Update the UI after signin
        updateUI(true);

        }

/**
 * Updating the UI, showing/hiding buttons and profile layout
 * */
private void updateUI(boolean isSignedIn) {
        System.out.println("CHECKINGFLOW "+" updateUI");

        if (isSignedIn) {
        btnSignIn.setVisibility(View.GONE);
        btnSignOut.setVisibility(View.VISIBLE);
        btnRevokeAccess.setVisibility(View.VISIBLE);
        llProfileLayout.setVisibility(View.VISIBLE);
        } else {
        btnSignIn.setVisibility(View.VISIBLE);
        btnSignOut.setVisibility(View.GONE);
        btnRevokeAccess.setVisibility(View.GONE);
        llProfileLayout.setVisibility(View.GONE);
        }
        }

/**
 * Fetching user's information name, email, profile pic
 * */

private void getProfileInformation() {
        System.out.println("CHECKINGFLOW "+" getProfileInformation");

        try {
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
        Person currentPerson = Plus.PeopleApi
        .getCurrentPerson(mGoogleApiClient);
        String personName = currentPerson.getDisplayName();
        String personPhotoUrl = currentPerson.getImage().getUrl();
        String personGooglePlusProfile = currentPerson.getUrl();
        String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

        Log.e(TAG, "Name: " + personName + ", plusProfile: "
        + personGooglePlusProfile + ", email: " + email
        + ", Image: " + personPhotoUrl);

        txtName.setText(personName);
        txtEmail.setText(email);

        // by default the profile url gives 50x50 px image only
        // we can replace the value with whatever dimension we want by
        // replacing sz=X
        personPhotoUrl = personPhotoUrl.substring(0,
        personPhotoUrl.length() - 2)
        + PROFILE_PIC_SIZE;

        new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);

        } else {
        Toast.makeText(getApplicationContext(),
                "Person information is null", Toast.LENGTH_LONG).show();
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        }

@Override
public void onConnectionSuspended(int arg0) {
        System.out.println("CHECKINGFLOW "+" onConnectionSuspended");

        mGoogleApiClient.connect();
        updateUI(false);
        }

/**
 * Button on click listener
 * */
@Override
public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_sign_in:
        // Signin button clicked
        signInWithGplus();
        break;
        case R.id.btn_sign_out:
        // Signout button clicked
        signOutFromGplus();
        break;
        case R.id.btn_revoke_access:
        // Revoke access button clicked
        revokeGplusAccess();
        break;
        }
        }

private void revokeGplusAccess() {
        System.out.println("CHECKINGFLOW "+" revokeGplusAccess");

        if (mGoogleApiClient.isConnected()) {
        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
        .setResultCallback(new ResultCallback<Status>() {
@Override
public void onResult(Status arg0) {
        Log.e(TAG, "User access revoked!");
        btnSignOut.setOnClickListener(MainActivity.this);
        mGoogleApiClient.connect();
        updateUI(false);
        }

        });
        }
        }

private void signOutFromGplus() {
        System.out.println("CHECKINGFLOW "+" signOutFromGplus");

        if (mGoogleApiClient.isConnected()) {
        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        mGoogleApiClient.disconnect();
        mGoogleApiClient.connect();
        updateUI(false);
        }
        }

/**
 * Sign-in into google
 * */
private void signInWithGplus() {
        System.out.println("CHECKINGFLOW "+" signInWithGplus");

        System.out.println(" signInWithGplus "+mGoogleApiClient.isConnecting());
        if (!mGoogleApiClient.isConnecting()) {
        System.out.println(" !not signInWithGplus "+mGoogleApiClient.isConnecting());
        mSignInClicked = true;
        resolveSignInError();
        }
        }

/**
 * Background Async task to load user profile picture from url
 * */
private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public LoadProfileImage(ImageView bmImage) {

        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
}