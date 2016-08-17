package com.example.nz160.animation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set main.XML as the layout for this Activity
        setContentView(R.layout.content_main);

        // Set the listener for Button_Next, a quick and dirty way to create a
        // listener
        Button buttonNext = (Button) findViewById(R.id.Button_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get the ViewFlipper from the layout
                ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);

                // Set an animation from res/anim: I pick push left in
                vf.setAnimation(AnimationUtils.loadAnimation(view.getContext(),
                        R.anim.left_to_right));
                vf.showNext();
            }
        });

        // Set the listener for Button_Previous, a quick and dirty way to create
        // a listener
        Button buttonPrevious = (Button) findViewById(R.id.Button_previous);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get the ViewFlipper from the layout
                ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
                // Set an animation from res/anim: I pick push left out
                vf.setAnimation(AnimationUtils.loadAnimation(view.getContext(),
                        R.anim.right_to_left));
                vf.showPrevious();
            }

        });

    }


}
