package com.example.nz160.tejaswini;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nz160 on 18-03-2016.
 */
public class Overview extends Fragment {
    private LinearLayout linearLayout;
    List<SetterGetter> product=new ArrayList<>();;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_overview, container, false);
        try {
            product = ServerConnection.getProducts(ServerConnection.jsonarray);
            System.out.println("Overview_product" + product.get(0).getOverView());
            final TextView textview = (TextView) v.findViewById(R.id.overview);
            textview.setPadding(0, 0, 0, 100);
            textview.setText(android.text.Html.fromHtml(product.get(0).getDescription()).toString() +
                    "\n\n" + android.text.Html.fromHtml(product.get(0).getOverView()).toString());
            textview.setMovementMethod(new ScrollingMovementMethod());
        } catch (Exception e){

        }
		return v;
   
    }
}
