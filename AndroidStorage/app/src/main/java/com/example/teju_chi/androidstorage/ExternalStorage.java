package com.example.teju_chi.androidstorage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Teju-Chi on 12/4/2015.
 */
public class ExternalStorage extends Activity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalstorage);
        editText=(EditText)findViewById(R.id.editText);
    }
    public void saveInternalCache(View view){
        String data = editText.getText().toString();
        File folder=getCacheDir();
        File file=new File(folder,"internal cache.txt");
        writeData(file,data);
        message("Data stored to internal cache");

    }
    public void loadInternalCache(View view){

    }

    public void saveExternalCache(View view){
        String data = editText.getText().toString();
        File folder=getExternalCacheDir();
        File file=new File(folder,"external cache.txt");
        if(data.equals("")){
            message("please enter data");
        } else {
            writeData(file, data);
        }
        message("Data stored to external cache");
    }
    public void loadExternalCache(View view){

    }

    public void saveFileExternalPublic(View view){
        String data = editText.getText().toString();
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file=new File(folder,"external public file.txt");
        writeData(file,data);
        message("Data stored to external public file");

    }
    public void loadExternalPublicFile(View view){

    }
    public void saveExternalFilePrivate(View view){
        String data = editText.getText().toString();
        File folder=getExternalFilesDir("Teju");
        File file=new File(folder,"external private file.txt");
        writeData(file,data);
        message("Data stored to external private file");
    }
    public void loadExternalPrivateFile(View view){

    }

    public void writeData(File file, String data){
        FileOutputStream fileOutputStream=null;
        Log.d("fileOutputStream show",file.toString()+" data "+data);

        try {
            fileOutputStream=new FileOutputStream(file);
            try {
                fileOutputStream.write(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("IOException stack", e.getMessage());
            }
            finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("FileNotFoundException exc",e.getMessage());
        }

    }
    public void message(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
