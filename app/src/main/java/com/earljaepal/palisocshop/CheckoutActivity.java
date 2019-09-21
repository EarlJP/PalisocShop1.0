package com.earljaepal.palisocshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckoutActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MenuActivity.class.getSimpleName();

    private static final double TPS = 0.05;
    private static final double TVQ = 0.09975;

    /**
     * Creates the Checkout Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Get the total from the previous menu activity
        Intent intent = getIntent();
        String stringTotal = (intent.getStringExtra(MenuActivity.EXTRA_MESSAGE)).substring(1);
        // Set the total before taxes with this intent
        TextView setTotal = findViewById(R.id.pretotal);

        Log.d(LOG_TAG, "Check the intent:  " + stringTotal);

        setTotal.setText("$" + stringTotal);

        calculateTotal(stringTotal);
    }

    /**
     * Method to calculate the final total
     * @param stringTotal
     */
    public void calculateTotal(String stringTotal) {
        double beforeTax;
        double afterTax;

        // Parse the string of the pre-total brought from the previous activity as an intent
        beforeTax = Double.parseDouble(stringTotal);
        // Calculate the final total
        afterTax = beforeTax + calculateTPS(beforeTax) + calculateTVQ(beforeTax);
        Log.d(LOG_TAG, "Check the calculation:  " + afterTax);

        // Set the final total
        TextView finalTotalText = findViewById(R.id.total);
        finalTotalText.setText(String.format("$%.2f", afterTax));
    }

    /**
     * Method to calculate the TPS to be added for the final total
     * @param beforeTotal
     * @return
     */
    public double calculateTPS(double beforeTotal) {
        double tax = beforeTotal * TPS;
        Log.d(LOG_TAG, "Check the calculation:  " + tax);
        TextView setTPS = findViewById(R.id.tps);
        setTPS.setText(String.format("%.2f", tax));
        return tax;
    }

    /**
     * Method to calculate the TVQ to be added for the final total
     * @param beforeTotal
     * @return
     */
    private double calculateTVQ(double beforeTotal) {
        double tax = beforeTotal * TVQ;
        Log.d(LOG_TAG, "Check the calculation:  " + tax);
        TextView setTPS = findViewById(R.id.tvq);
        setTPS.setText(String.format("%.2f", tax));
        return tax;
    }
}
