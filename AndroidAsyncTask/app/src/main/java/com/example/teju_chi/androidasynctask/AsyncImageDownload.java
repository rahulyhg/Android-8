package com.example.teju_chi.androidasynctask;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Teju-Chi on 12/7/2015.
 */
public class AsyncImageDownload extends Activity implements AdapterView.OnItemClickListener {
    EditText display_url;
    ListView listView;
    private String[] imagesurl;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_download_image);
        display_url=(EditText)findViewById(R.id.textView);
        listView=(ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        imagesurl=getResources().getStringArray(R.array.imageurls);
    }

    public void downloadImage(View view){


        String url=display_url.getText().toString();
        System.out.println("display_url "+url);
        new MyClass().execute(url);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        display_url.setText(imagesurl[position]);
    }

    class  MyClass extends AsyncTask<String, Integer, Boolean> {

        private int counter;
        private int calculatedProgress=0;
        private int contentLength;

        @Override
        protected Boolean doInBackground(String... params) {
            URL downloadurl=null;
            InputStream inputStream=null;
            boolean success=false;
            File file=null;
            FileOutputStream fileOutputStream=null;
            HttpURLConnection connection=null;
            //To get only last part of the url that is image name
            Uri uri=Uri.parse(params[0]);
            String image_name=uri.getLastPathSegment();
            try {
                file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsoluteFile()+"/"+image_name);
                fileOutputStream=new FileOutputStream(file);
                downloadurl=new URL(params[0]);
                connection= (HttpURLConnection) downloadurl.openConnection();
                contentLength=connection.getContentLength();
                inputStream=connection.getInputStream();

                //Reads 1024 byes at a time
                byte[] buffers=new byte[1024];
                int read=-1;
                while((read=inputStream.read(buffers))!=-1)
                {
                    //Write the byte char from 0  to how many are read
                    fileOutputStream.write(buffers,0,read);
                    counter=counter+read;
                    publishProgress(counter);
                }
            } catch (IOException e) {


                e.printStackTrace();

            } finally {

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

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setVisibility(View.VISIBLE);
            calculatedProgress=((int) (((double)values[0]/contentLength)*1000));
            progressBar.setProgress(calculatedProgress);


        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplication(),"Download Complete",Toast.LENGTH_LONG).show();
        }
    }
}
