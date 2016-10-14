package com.example.nz160.downloadmultipleimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nz160 on 12-10-2016.
 */
public class BannerAdapter extends BaseAdapter {
    public static final String BaseUrl = "http://192.168.2.79:8080/Compliance4AllBackend/";

    private final Context context;
    private List<SetterGetter> images=new ArrayList<>();
    static String url =BaseUrl+"BannerImages";

    public BannerAdapter(Context context,List<SetterGetter> images){
        this.context=context;
        this.images=images;

    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.banner_content, parent, false);
        } else {
            row = convertView;
        }
        ImageView imageView=(ImageView)row.findViewById(R.id.imageview);
        Picasso.with(context)
                .load(url + "?image=" +images.get(position).getImages() )
                .placeholder(R.drawable.launchericon) // optional
                .error(R.drawable.launchericon)         // optional
                .into(imageView);
        return row;
    }
}
