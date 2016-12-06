package com.example.nz160.volleyexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nz160.interfacetest.interfaceTest;

public class MainActivity extends AppCompatActivity implements interfaceTest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int i=test();
        System.out.println("HELOOOOO "+i);

    }

    @Override
    public int test() {
        System.out.println("HELOOOOO");
        return 10;
    }
}
