package com.example.teju_chi.androidbaseclassadapters;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by Teju-Chi on 12/11/2015.
 */
public class ArrayClassAdapter extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] tittle=getResources().getStringArray(R.array.Title);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,tittle));
    }
}
