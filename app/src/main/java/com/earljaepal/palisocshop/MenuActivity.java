package com.earljaepal.palisocshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MenuActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE =
            "com.earljaepal.palisocshop.extra.MESSAGE";

    private static final String NO_ITEMS = "Sorry, this item is not in your cart!";

    private int quantityCount = 0;
    private TextView finalTotal;

    /**
     * Creates the Menu Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Assigns the total as a TextView to be sent to the Checkout
        finalTotal = findViewById(R.id.total);
    }

    /**
     * Launches the Checkout menu and sends the appropriate data
     * @param view
     */
    public void launchCheckoutMenu(View view) {
        Intent checkout = new Intent(this, CheckoutActivity.class);
        String stringTotal = finalTotal.getText().toString();
        checkout.putExtra(EXTRA_MESSAGE, stringTotal);

        startActivity(checkout);
        Log.d(LOG_TAG, "Proceed to checkout");
    }

    /**
     * Removes the quantity of the item corresponding to the '-' button clicked
     * @param view
     */
    public void removeItem(View view) {
        // Access the quantity
        Button buttonClicked = findViewById(view.getId());
        ViewGroup quantityView = (ViewGroup) buttonClicked.getParent();
        TextView displayQuantity = (TextView) quantityView.getChildAt(2);

        quantityCount = Integer.parseInt(displayQuantity.getText().toString());

        Log.d(LOG_TAG, "Check the quantity:  " + quantityCount);

        // Reduce and set the quantity. If quantity is zero, notify with a pop up message
        if (quantityCount > 0) {
            quantityCount--;
            // Display the new quantity
            displayQuantity.setText(Integer.toString(quantityCount));

            // Update the subtotal depending on the new quantity
            updateSubtotal((ViewGroup) quantityView.getParent(), quantityCount);
        }
        else
            // Send a popup message if the quantity is already zero
            Toast.makeText(this, NO_ITEMS, Toast.LENGTH_SHORT).show();
    }

    /**
     * Adds to the quantity of the item corresponding to the '+' button clicked
     * @param view
     */
    public void addItem(View view) {
        // Access the quantity
        Button buttonClicked = findViewById(view.getId());
        ViewGroup quantityView = (ViewGroup) buttonClicked.getParent();
        TextView displayQuantity = (TextView) quantityView.getChildAt(2);

        // Increment and set the quantity
        quantityCount = Integer.parseInt(displayQuantity.getText().toString());

        Log.d(LOG_TAG, "Check the quantity:  " + quantityCount);

        ++quantityCount;

        // Display the new quantity
        displayQuantity.setText(Integer.toString(quantityCount));

        // Update the subtotal depending on the new quantity
        updateSubtotal((ViewGroup) quantityView.getParent(), quantityCount);
    }

    /**
     * Method that updates the subtotal text when the quantity buttons are clicked
     * @param view
     * @param quantity
     */
    public void updateSubtotal(ViewGroup view, int quantity) {
        // Access the appropriate view
        ViewGroup subtotalView = (ViewGroup) view.getChildAt(5);
        TextView displaySubtotal = (TextView) subtotalView.getChildAt(1);

        TextView price = getPrice(view);

        Log.d(LOG_TAG, "Check the price:  " + price);

        // Extract the subtotal TextView and make the calculations
        double subtotal = Double.parseDouble(price.getText().toString());

        subtotal *= quantity;

        // Set the new subtotal
        displaySubtotal.setText(String.format("%.2f", subtotal));
        updateTotal();
    }

    /**
     * Method that gets the price of the appropriate item
     * @param view
     * @return
     */
    public TextView getPrice(ViewGroup view) {
        return (TextView) view.getChildAt(2);
    }

    /**
     * Method that adds the current subtotals and updates the total TextView whenever buttons
     * are clicked
     */
    public void updateTotal() {
        // Extract the subtotals of each item via their id
        TextView subtotal_1 = findViewById(R.id.initial_subtotal1);
        TextView subtotal_2 = findViewById(R.id.initial_subtotal2);
        TextView subtotal_3 = findViewById(R.id.initial_subtotal3);

        // Add all the current subtotals
        double currentTotal = Double.parseDouble(subtotal_1.getText().toString());
        currentTotal += Double.parseDouble(subtotal_2.getText().toString());
        currentTotal += Double.parseDouble(subtotal_3.getText().toString());

        // Set the new total
        ((TextView) findViewById(R.id.total)).setText(String.format("$%.2f", currentTotal));
    }
}
