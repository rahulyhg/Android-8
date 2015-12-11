package com.example.teju_chi.androidbaseclassadapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    ListView listView;
    String[] tittle;
    String[] desc;
    int image_id;
    BaseClass baseclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);

        ArrayList<BaseClass> arrayList=new ArrayList<BaseClass>();

        tittle=getResources().getStringArray(R.array.Title);
        desc=getResources().getStringArray(R.array.Desc);
        image_id=(R.drawable.ic_launcher);
        int i=0;

        for(String title:tittle) {
          baseclass = new BaseClass(title, desc[i], image_id);
            arrayList.add(baseclass);
            i++;
        }

        BaseClassAdapter b=new BaseClassAdapter(this,arrayList);
        listView.setAdapter(b);
        int count=b.getCount();
        Log.d("getCount()","getCount() is "+count);
        Log.d("getCount","getItem is "+b.getItem(3).toString());
        Log.d("getCount ","getItemId is "+b.getItemId(1));
    }

    public void arrayAdapter(View view){
        Intent i=new Intent(this,ArrayClassAdapter.class);
        startActivity(i);
    }
}
