package com.example.teju_chi.androidstorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;


public class ActivitySharedPreference extends Activity {

    EditText name;
    EditText pswd;
    private FileOutputStream output;
    private String Default="NA";
    private TextView username;
    private TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);

        name=(EditText)findViewById(R.id.user_name);
        pswd=(EditText)findViewById(R.id.pass);
    }

    public void saveSharedPrefences(View view){

        SharedPreferences sharedPreferences=getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",name.getText().toString());
        editor.putString("password",pswd.getText().toString());
        editor.commit();
    }

    public void loadSharedPrefences(View view){

        SharedPreferences sharedPreferences=getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("name",Default);
        String pswd=sharedPreferences.getString("password",Default);

            username=(TextView)findViewById(R.id.user_name);
            username.setText(name);
            pass=(TextView)findViewById(R.id.pass);
            pass.setText(pswd);
    }

    public void internalStorage(View view){
        Intent i=new Intent(this,ActivityInternalStorage.class);
        startActivity(i);
    }
}
