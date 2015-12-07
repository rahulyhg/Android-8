package com.example.teju_chi.androidasynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    String[] texts={"a","b","c","d","e","f","g","h","i","j","k","l","m"};
    ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_main);
        mainList=(ListView)findViewById(R.id.listView);
        mainList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new
                ArrayList<String>()));
        new MyAsyncClass().execute();
    }

    class MyAsyncClass extends AsyncTask<Void,String,Void>{

        ArrayAdapter<String> arrayAdapter;
        int count=0;
        @Override
        protected void onPreExecute() {
            arrayAdapter= (ArrayAdapter<String>) mainList.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(String item:texts){
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            arrayAdapter.add(values[0]);
            count++;
            setProgress((int) (((double)count/texts.length)*1000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
            Toast.makeText(getApplication(),"Items are added successfully",Toast.LENGTH_LONG).show();
        }

    }

public void downloadImageButton(View view){
    Intent i=new Intent(this,AsyncImageDownload.class);
    startActivity(i);
}



}
