package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GTADrive extends AppCompatActivity {

    private View mContentView;

    final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    //First thing to run; acts as a constructor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtadrive);

        mContentView = findViewById(R.id.fullscreen_content_gta);

        mContentView.setSystemUiVisibility(FULLSCREEN);
    }
}