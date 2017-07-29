package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.R.color.holo_red_dark;
import static android.R.color.holo_red_light;
import static com.atomicbrawlers.arduinodriver.R.style.FullscreenTheme;


public class TankDrive extends AppCompatActivity {

    private View mContentView;
    private Button //mStop,
            mEmergencyStop;
    private SeekBar mLeftDriveInput, mRightDriveInput;
    private TextView mLeftDrivePercentage, mRightDrivePercentage;

    private boolean emergencyStop;
    private int leftInput, rightInput,
                leftSpeed, rightSpeed;

    private final int RELATIVE_ZERO = 100;
    private final int THRESH = 30;
    private final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_drive);

        mContentView = findViewById(R.id.fullscreen_content_tank);
        mContentView.setSystemUiVisibility(FULLSCREEN);

     //   mStop                 = (Button)   findViewById(R.id.stop_tank);
        mEmergencyStop        = (Button)   findViewById(R.id.emergency_stop_tank);
        mLeftDriveInput       = (SeekBar)  findViewById(R.id.left_drive_bar_tank);
        mRightDriveInput      = (SeekBar)  findViewById(R.id.right_drive_bar_tank);
        mLeftDrivePercentage  = (TextView) findViewById(R.id.left_drive_percentage_tank);
        mRightDrivePercentage = (TextView) findViewById(R.id.right_drive_percentage_tank);

        setUpListeners();

        emergencyStop = false;
        leftInput  = RELATIVE_ZERO;
        rightInput = RELATIVE_ZERO;
        leftSpeed  = 0;
        rightSpeed = 0;
    }

    private void setUpListeners(){
        mLeftDriveInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                leftInput = progress;
                interpretInput();
                mLeftDrivePercentage.setText(String.valueOf(leftSpeed) + "%");
                sendToRobot();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });

        mRightDriveInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rightInput = progress;
                interpretInput();
                mRightDrivePercentage.setText(String.valueOf(rightSpeed) + "%");
                sendToRobot();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });
    }

    public void sendToRobot(){
        if (!emergencyStop) {
            //TODO: Send values through Bluetooth here
        }
    }

    public void emergencyStop(View view){
        emergencyStop = true;
        stop(view);

        mEmergencyStop.setBackgroundColor(getResources().getColor(holo_red_dark,  this.getTheme()));
        mEmergencyStop.setTextColor      (getResources().getColor(holo_red_light, this.getTheme()));
        mEmergencyStop.setText           ("EMERGENCY STOPPED");
    }

    public void stop(View view){
        leftSpeed  = 0;
        rightSpeed = 0;

        sendToRobot();

        mLeftDrivePercentage .setText(String.valueOf(leftSpeed)  + "%");
        mRightDrivePercentage.setText(String.valueOf(rightSpeed) + "%");

        mLeftDriveInput. setProgress(RELATIVE_ZERO);
        mRightDriveInput.setProgress(RELATIVE_ZERO);
    }

    public void interpretInput(){ //Converts raw SeekBar values to appropriate motor values
        if (Math.abs(leftInput - RELATIVE_ZERO) > THRESH){ //Check left input
            leftSpeed = leftInput - RELATIVE_ZERO;
        } else {
            leftSpeed = 0;
        }

        if (Math.abs(rightInput - RELATIVE_ZERO) > THRESH){ //Check right input
            rightSpeed = rightInput - RELATIVE_ZERO;
        } else {
            rightSpeed = 0;
        }
    }
}