package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

//TODO: Connect percentage TextViews to SeekBars

public class TankDrive extends AppCompatActivity {

    private View mContentView;
    private Button mStop, mEmergencyStop;
    private SeekBar mLeftDriveInput, mRightDriveInput;
    private TextView mLeftDrivePercentage, mRightDrivePercentage;

    final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    //First thing to run; acts as a constructor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_drive);

        mContentView = findViewById(R.id.fullscreen_content_tank);
        mContentView.setSystemUiVisibility(FULLSCREEN);

        mStop                 = (Button)   findViewById(R.id.stop_tank);
        mEmergencyStop        = (Button)   findViewById(R.id.emergency_stop_tank);
        mLeftDriveInput       = (SeekBar)  findViewById(R.id.left_drive_bar_tank);
        mRightDriveInput      = (SeekBar)  findViewById(R.id.right_drive_bar_tank);
        mLeftDrivePercentage  = (TextView) findViewById(R.id.left_drive_percentage_tank);
        mRightDrivePercentage = (TextView) findViewById(R.id.right_drive_percentage_tank);
    }
}