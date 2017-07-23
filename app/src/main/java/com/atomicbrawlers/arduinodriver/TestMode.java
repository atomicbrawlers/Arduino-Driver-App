package com.atomicbrawlers.arduinodriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

// TODO: Make speed input reset when you select it to type on; there should be an easy way to do this.
// TODO: Fix swiping to show UI changing the speed slider value; could cause problems upon exiting.

public class TestMode extends AppCompatActivity {

    private View mContentView;

    final int FULLSCREEN = View.SYSTEM_UI_FLAG_IMMERSIVE
                         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                         | View.SYSTEM_UI_FLAG_FULLSCREEN
                         | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    private EditText.OnEditorActionListener checkKeyboardExit = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE /*||
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                            (event.getKeyCode() == KeyEvent.KEYCODE_BACK ||
                             event.getKeyCode() == KeyEvent.KEYCODE_HOME ||
                             event.getKeyCode() == KeyEvent.KEYCODE_MENU)*/) {
                recreate();
                return true; //user is done typing
            }
            return false; //pass on to other listeners
        }
    };

    @Override
    //First thing to run; acts as a constructor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_test);

        mContentView = findViewById(R.id.fullscreen_content);

        mContentView.setSystemUiVisibility(FULLSCREEN);

        EditText speedInput = (EditText) findViewById(R.id.speed_input_test);
        speedInput.setOnEditorActionListener(checkKeyboardExit);
    }
}