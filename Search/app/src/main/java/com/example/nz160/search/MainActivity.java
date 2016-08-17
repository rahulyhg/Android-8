package com.example.nz160.search;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    ListView listview;
    int textlength = 0;
    ArrayList<String> text_sort = new ArrayList<String>();
    ArrayList<Integer> image_sort = new ArrayList<Integer>();

    String[] text = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten" };

    int[] image = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        edittext = (EditText) findViewById(R.id.EditText01);
        listview = (ListView) findViewById(R.id.ListView01);
        listview.setAdapter(new MyCustomAdapter(text, image,this));


        edittext.addTextChangedListener(new TextWatcher()
        {

            public void afterTextChanged(Editable s)
            {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after)
            {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count)
            {

                textlength = edittext.getText().length();
                text_sort.clear();
                image_sort.clear();

                for (int i = 0; i < text.length; i++)
                {
                    if (textlength <= text[i].length())
                    {
                        if (edittext.getText().toString().
                                equalsIgnoreCase((String) text[i].subSequence(0, textlength)))
                        {
                            text_sort.add(text[i]);
                            image_sort.add(image[i]);
                        }
                    }
                }

                listview.setAdapter(new MyCustomAdapter
                        (text_sort, image_sort,MainActivity.this ));

            }
        });
    }

}

class MyCustomAdapter extends BaseAdapter
{

    private  Context context;
    String[] data_text;
    int[] data_image;

    MyCustomAdapter()
    {

    }

    MyCustomAdapter(String[] text, int[] image,Context context)
    {
        data_text = text;
        this.context = context;
        data_image = image;
    }

    MyCustomAdapter(ArrayList<String> text, ArrayList<Integer> image,Context context)
    {
        this.context = context;
        data_text = new String[text.size()];
        data_image = new int[image.size()];

        for(int i=0;i<text.size();i++)
        {
            data_text[i] = text.get(i);
            data_image[i] = image.get(i);
        }

    }

    public int getCount()
    {
        return data_text.length;
    }

    public String getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;

        row = inflater.inflate(R.layout.listview, parent, false);

        TextView textview = (TextView) row.findViewById(R.id.TextView01);
        ImageView imageview = (ImageView) row
                .findViewById(R.id.ImageView01);

        textview.setText(data_text[position]);
        imageview.setImageResource(data_image[position]);

        return (row);

    }
}



