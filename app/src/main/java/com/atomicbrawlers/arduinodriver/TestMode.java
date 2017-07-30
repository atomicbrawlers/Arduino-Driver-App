package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import static android.R.color.holo_red_dark;
import static android.R.color.holo_red_light;

// TODO: Fix swiping to show UI changing the speed slider value; could cause problems upon exiting.

public class TestMode extends AppCompatActivity {

    private View mContentView;
    private Button //mStop,
                   mEmergencyStop;
    private ToggleButton mFrontLeft, mFrontRight,
                         mBackLeft,  mBackRight;
    private SeekBar mSpeedBar;
    private EditText mSpeedText;

    private boolean emergencyStop,
                    frontLeft, frontRight,
                    backLeft,  backRight;
    private int speed;

    private final int RELATIVE_ZERO = 100;
//    private final int THRESH = 30; //Currently not used in TestMode
    private final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
                                 | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                 | View.SYSTEM_UI_FLAG_FULLSCREEN
                                 | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    class BluetoothTransfer implements Runnable {
        private volatile boolean exit = false;

        public void run(){
            while(!exit) { //thread runs inside loop
                System.out.println("Running Thread");
                //TODO: Send values through Bluetooth here
                //Send left speed
                //Send right speed
            } //thread stops outside loop
        }

        public void stop(){
            //TODO: Run values of ZERO here to ensure robot stops
            exit = true;
        }
    }

    private final BluetoothTransfer rDataTransfer= new BluetoothTransfer();

    private final Thread tDataTransfer = new Thread(rDataTransfer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_test);

        mContentView = findViewById(R.id.fullscreen_content_test);
        mContentView.setSystemUiVisibility(FULLSCREEN);

     //   mStop = (Button)findViewById(R.id.stop_motors_test);
        mEmergencyStop = (Button)       findViewById(R.id.emergency_stop_test);
        mFrontLeft     = (ToggleButton) findViewById(R.id.left_front_motor_test);
        mFrontRight    = (ToggleButton) findViewById(R.id.right_front_motor_test);
        mBackLeft      = (ToggleButton) findViewById(R.id.left_back_motor_test);
        mBackRight     = (ToggleButton) findViewById(R.id.right_back_motor_test);
        mSpeedBar      = (SeekBar)      findViewById(R.id.speed_input_bar_test);
        mSpeedText     = (EditText)     findViewById(R.id.speed_input_text_test);

        emergencyStop = false;
        resetVariables();
        setUpListeners();

        //if (!emergencyStop) //might need based off the following to-do
        tDataTransfer.start(); //TODO: Make sure the EditTextListener recreate() doesn't mess with this
    }

    @Override
    protected void onPause(){
        super.onPause();
        rDataTransfer.stop();
    }

    private void resetVariables(){
        frontLeft  = false;
        frontRight = false;
        backLeft   = false;
        backRight  = false;
        speed      = 0;
    }

    private void setUpListeners(){
        mSpeedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = progress - RELATIVE_ZERO;
                mSpeedText.setText(String.valueOf(speed));
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

        mSpeedText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE /*||
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                            (event.getKeyCode() == KeyEvent.KEYCODE_BACK ||
                             event.getKeyCode() == KeyEvent.KEYCODE_HOME ||
                             event.getKeyCode() == KeyEvent.KEYCODE_MENU)*/) {
                    recreate();
                    if (!mSpeedText.getText().toString().equals("")) { //User input a speed; change the speed
                        speed = Integer.parseInt(mSpeedText.getText().toString());
                        mSpeedBar.setProgress(speed + RELATIVE_ZERO);
                    } else { //Do not change the speed
                        System.out.println("No speed input");
                        mSpeedText.setText(String.valueOf(speed));
                        System.out.println("Changed back to old speed");
                    }
                    return true; //user is done typing
                }
                return false; //pass on to other listeners
            }
        });
    }

    public void emergencyStop(View view){
        emergencyStop = true;
        stop(view);
        rDataTransfer.stop();

        mEmergencyStop.setBackgroundColor(getResources().getColor(holo_red_dark,  this.getTheme()));
        mEmergencyStop.setTextColor      (getResources().getColor(holo_red_light, this.getTheme()));
        mEmergencyStop.setText           ("EMERGENCY STOPPED");
    }

    public void stop(View view){
        resetVariables();

        mFrontLeft  .setChecked(false);
        mFrontRight .setChecked(false);
        mBackLeft   .setChecked(false);
        mBackRight  .setChecked(false);

        mSpeedText .setText(String.valueOf(speed));
        mSpeedBar  .setProgress(RELATIVE_ZERO);
    }

    public void clearSpeedInput(View view){
        mSpeedText.setText(String.valueOf(""));
    }

    public void toggleFrontLeft(View view){
        frontLeft = !frontLeft;
    }

    public void toggleFrontRight(View view){
        frontRight = !frontRight;
    }

    public void toggleBackLeft(View view){
        backLeft = !backLeft;
    }

    public void toggleBackRight(View view){
        backRight = !backRight;
    }
}