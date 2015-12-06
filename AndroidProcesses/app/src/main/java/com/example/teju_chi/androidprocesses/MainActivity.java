package com.example.teju_chi.androidprocesses;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        editText=(EditText)findViewById(R.id.editText);
    }

   public void saveFile(View view){
       String data=editText.getText().toString() ;
       File file=null;
       FileOutputStream fileOutputStream=null;
       file=getFilesDir();
       try {
           fileOutputStream=openFileOutput("teju.txt", Context.MODE_PRIVATE);
           fileOutputStream.write(data.getBytes());
           textView.setTextColor(Color.GREEN);
           textView.setText(data +"\nwritten to "+file.getAbsolutePath());
       } catch (FileNotFoundException e) {
           e.printStackTrace();
           textView.setTextColor(Color.RED);
           textView.setText(e.toString());
       } catch (IOException e) {
           e.printStackTrace();
           textView.setTextColor(Color.RED);
           textView.setText(e.toString());
       }
   }

}
