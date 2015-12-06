package com.example.teju_chi.androidprocess2;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    TextView loadData;
    String packageName="com.example.teju_chi.androidprocesses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData=(TextView)findViewById(R.id.textView);
    }

    public void loadFile(View view){

        PackageManager packageManager=getPackageManager();
        try {
            ApplicationInfo applicationInfo=packageManager.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            String fullpath=applicationInfo.dataDir+"/files/teju.txt";
            readFile(fullpath);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void readFile(String fullPath){

        FileInputStream fileInputStream=null;

        try {
            fileInputStream=new FileInputStream(new File(fullPath));
            StringBuffer stringBuffer=new StringBuffer();
            int read=-1;
            while ((read=fileInputStream.read())!=-1){
                stringBuffer.append((char)read);
            }
            loadData.setText(stringBuffer);
            loadData.setTextColor(Color.BLACK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            loadData.setText(e.toString());
            loadData.setTextColor(Color.RED);
        } catch (IOException e) {
            e.printStackTrace();
            loadData.setText(e.toString());
            loadData.setTextColor(Color.RED);
        }

    }
}
