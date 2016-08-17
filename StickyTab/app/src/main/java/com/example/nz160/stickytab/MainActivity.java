package com.example.nz160.stickytab;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                collapsingToolbarLayout.setTitle("Toolbar Animation Demo");//Set Title over collapsing toolbar layout
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.webinar_detail_bg);

                // It will generate colors based on the image in an AsyncTask.
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @SuppressWarnings("ResourceType")
                    @Override
                    public void onGenerated(Palette palette) {

                        int mutedColor = palette.getMutedColor(R.color.colorPrimary);
                        collapsingToolbarLayout.setContentScrimColor(mutedColor);
                        collapsingToolbarLayout.setStatusBarScrimColor(R.color.black_trans);
                    }
                });

    }
}
