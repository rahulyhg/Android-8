package com.example.nz160.popupmenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private PopupMenu popupMenu;
    private final static int ONE = 1;
    private final static int TWO = 2;
    private final static int THREE = 3;
     TextView button;
    private String[] popUpContents;
    PopupWindow popupWindowDogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button=(TextView) findViewById(R.id.anchor) ;
        popupMenu = new PopupMenu(this, findViewById(R.id.anchor));
        popupMenu.getMenu().add(Menu.NONE, ONE, Menu.NONE, "Item 1");
        popupMenu.getMenu().add(Menu.NONE, TWO, Menu.NONE, "Item 2");
        popupMenu.getMenu().add(Menu.NONE, THREE, Menu.NONE, "Item 3");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        List<String> dogsList = new ArrayList<String>();
        dogsList.add("Akita Inu::1");
        dogsList.add("Alaskan Klee Kai::2");
        dogsList.add("Papillon::3");
        dogsList.add("Tibetan Spaniel::4");

        // convert to simple array
        popUpContents = new String[dogsList.size()];
        dogsList.toArray(popUpContents);


        // initialize pop up window
        popupWindowDogs = popupWindowDogs();

        findViewById(R.id.anchor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // popupMenu.show();
                /*PopupWindow popupwindow_obj = popupDisplay();
                popupwindow_obj.showAsDropDown(button, 60, 50);*/
                popupWindowDogs.showAsDropDown(button, 60, 50);

            }
        });
    }
    public PopupWindow popupDisplay()
    {

        final PopupWindow popupWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup, null);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(button, 0,0);

        return popupWindow;
    }
    public PopupWindow popupWindowDogs() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewDogs = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewDogs.setAdapter(dogsAdapter(popUpContents,listViewDogs));

        // set the item click listener
        listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);


        return popupWindow;
    }

    /*
     * adapter where the list values will be set
     */
    private ArrayAdapter<String> dogsAdapter(String dogsArray[], final ListView listView) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogsArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
               // listView.getChildAt(2).setEnabled(false);

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                // visual settings for the list item
                TextView listItem = new TextView(MainActivity.this);

                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);


                return listItem;
            }
        };

        return adapter;
    }
}
