package com.example.androidhive;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InboxActivity extends ListActivity {
    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox_list);
        listView = (ListView) findViewById(android.R.id.list);
      /*  String[] values = new String[] { "View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);*/
      //  InboxAdapter adapter=new InboxAdapter(this);
       // listView.setAdapter(adapter);
    }
}