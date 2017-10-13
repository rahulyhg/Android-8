package com.example.nz160.popupmenu;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class DogsDropdownOnItemClickListener implements OnItemClickListener {

    String TAG = "DogsDropdownOnItemClickListener.java";

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

        // get the context and main activity to access variables
        Context mContext = v.getContext();
        MainActivity mainActivity = ((MainActivity) mContext);

        // add some animation when a list item was clicked
        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(10);
        v.startAnimation(fadeInAnimation);

        // dismiss the pop up
        mainActivity.popupWindowDogs.dismiss();

        // get the text and set it as the button text
        String selectedItemText = ((TextView) v).getText().toString();
        mainActivity.button.setText(selectedItemText);

        // get the id
        String selectedItemTag = ((TextView) v).getTag().toString();
        Toast.makeText(mContext, "Dog ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

    }

}