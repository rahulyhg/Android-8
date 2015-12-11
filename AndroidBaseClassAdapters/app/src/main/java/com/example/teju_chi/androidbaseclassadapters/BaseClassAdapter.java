package com.example.teju_chi.androidbaseclassadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Teju-Chi on 12/10/2015.
 */
public class BaseClassAdapter extends ArrayAdapter<BaseClass> {

     int resource;
    Context context;
    private ArrayList<BaseClass> arrayList;

    public BaseClassAdapter(Context context, int resource, ArrayList<BaseClass> arrayList){
        super(context,resource,arrayList);
        this.context=context;
        this.arrayList=arrayList;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseClass baseClass=arrayList.get(position);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.activity_adapter,parent,false);
        TextView title=(TextView)row.findViewById(R.id.textView);
        title.setText(baseClass.title);
        TextView desc=(TextView)row.findViewById(R.id.textView2);
        desc.setText(baseClass.desc);
        ImageView imageView=(ImageView)row.findViewById(R.id.imageView);
        imageView.setImageResource(baseClass.image_id);

        return row;
    }
}
