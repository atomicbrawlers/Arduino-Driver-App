package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

//TODO: Connect percentage TextViews to SeekBars

public class GTADrive extends AppCompatActivity {

    private View mContentView;
    private Button mStop, mEmergencyStop;
    private SeekBar mTurningInput, mSpeedInput;
    private TextView mTurningPercentage, mSpeedPercentage;

    final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    //First thing to run; acts as a constructor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gta_drive);

        mContentView = findViewById(R.id.fullscreen_content_gta);
        mContentView.setSystemUiVisibility(FULLSCREEN);

        mStop              = (Button)   findViewById(R.id.stop_gta);
        mEmergencyStop     = (Button)   findViewById(R.id.emergency_stop_gta);
        mTurningInput      = (SeekBar)  findViewById(R.id.direction_bar_gta);
        mSpeedInput        = (SeekBar)  findViewById(R.id.drive_bar_gta);
        mTurningPercentage = (TextView) findViewById(R.id.direction_bar_percentage_gta);
        mSpeedPercentage   = (TextView) findViewById(R.id.drive_bar_percentage_gta);
    }
}