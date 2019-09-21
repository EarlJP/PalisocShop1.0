package com.earljaepal.palisocshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    /**
     * Creates the Main Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launched the Menu Activity
     * @param view
     */
    public void launchActivityMenu(View view) {
        Intent startBrowse = new Intent(this, MenuActivity.class);
        startActivity(startBrowse);
        Log.d(LOG_TAG, "Launch the browse window");
    }
}
