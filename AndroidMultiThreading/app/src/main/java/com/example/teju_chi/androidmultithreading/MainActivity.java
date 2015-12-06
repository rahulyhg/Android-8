package com.example.teju_chi.androidmultithreading1;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.Buffer;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    EditText display_url;
    ListView listView;
    private String[] imagesurl;
    ProgressBar progressBar;
    LinearLayout loadingSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display_url=(EditText)findViewById(R.id.textView);
        listView=(ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        loadingSec=(LinearLayout)findViewById(R.id.linearlayout);
        imagesurl=getResources().getStringArray(R.array.imageurls);
    }

    public void downloadImage(View view){


        String url=display_url.getText().toString();
        System.out.println("display_url "+url);
        Thread mythread=new Thread(new DownnloadImages(url));
        mythread.start();
    }


    public boolean downloadImagesUsingThreads(String url) {

        URL downloadurl=null;
        InputStream inputStream=null;
        boolean success=false;
        File file=null;
        FileOutputStream fileOutputStream=null;
        HttpURLConnection connection=null;
        //To get only last part of the url that is image name
        Uri uri=Uri.parse(url);
        String image_name=uri.getLastPathSegment();
        try {
            file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .getAbsoluteFile()+"/"+image_name);
            fileOutputStream=new FileOutputStream(file);
            downloadurl=new URL(url);
            connection= (HttpURLConnection) downloadurl.openConnection();
            inputStream=connection.getInputStream();

            //Reads 1024 byes at a time
            byte[] buffers=new byte[1024];
            int read=-1;
            while((read=inputStream.read(buffers))!=-1)
                {
                    //Write the byte char from 0  to how many are read
                    fileOutputStream.write(buffers,0,read);
                }
        } catch (IOException e) {
            display_url.setText(e.toString());


            e.printStackTrace();

        } finally {
            //close progress bar after downloading
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSec.setVisibility(View.GONE);
                }
            });
            if(connection!=null){
                connection.disconnect();

            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;

    }

    public class DownnloadImages implements Runnable {

        String url;
        public DownnloadImages(String url){
            this.url=url;
        }

        @Override
        public void run() {

            //Download images class is running on background thread. But Ui elements cannot run on
            //background thread.so we create ui thread to run ui elements to show progress bar while
            // downloading
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSec.setVisibility(View.VISIBLE);
                }
            });
            downloadImagesUsingThreads(url);


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        display_url.setText(imagesurl[position]);
    }


}
