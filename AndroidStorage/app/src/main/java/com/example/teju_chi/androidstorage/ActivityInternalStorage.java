package com.example.teju_chi.androidstorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Teju-Chi on 12/3/2015.
 */
public class ActivityInternalStorage extends Activity {

    EditText name;
    EditText pswd;
    private FileOutputStream output;
    private String Default="NA";
    private TextView username;
    private TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internalstorage);

        name=(EditText)findViewById(R.id.user_name);
        pswd=(EditText)findViewById(R.id.pass);
    }

    public void saveInternalStorage(View view){

        String username=name.getText().toString();
        String pass=pswd.getText().toString();
        try {
            output=openFileOutput("teju.txt", Context.MODE_PRIVATE);
            output.write(username.getBytes());
            output.write(pass.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(getApplicationContext(),"saved successfully", Toast.LENGTH_LONG).show();

    }

    public void loadInternalStorage(View view){
       try {
            StringBuffer buffer=new StringBuffer();
            FileInputStream input=openFileInput("teju.txt");
            int read=-1;
            while((read=input.read())!=-1){
                buffer.append((char)read);
            }
            Log.d("FileInputStreamData ", buffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void externalStorage(View view){
        Intent i=new Intent(this,ExternalStorage.class);
        startActivity(i);
    }
}
