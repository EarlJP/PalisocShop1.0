package com.earljaepal.palisocshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MenuActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void launchCheckoutMenu(View view) {
        Intent checkout = new Intent(this, CheckoutActivity.class);
        startActivity(checkout);
        Log.d(LOG_TAG, "Proceed to checkout");
    }
}
