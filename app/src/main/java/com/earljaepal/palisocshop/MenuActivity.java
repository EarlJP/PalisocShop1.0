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
    private static final String NO_ITEMS = "Sorry, this item is not in your cart!";

    private int quantityCount = 0;
    private double subtotal = 0;
    private double total = 0;


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

    public void removeItem(View view) {
        // Set the quantity and reduce. If quantity is zero, leave it
        Button buttonClicked = findViewById(view.getId());
        ViewGroup quantityView = (ViewGroup) buttonClicked.getParent();
        TextView displayQuantity = (TextView) quantityView.getChildAt(2);

        quantityCount = Integer.parseInt(displayQuantity.getText().toString());

        if (quantityCount > 0) {
            quantityCount--;
            // Display the new quantity
            displayQuantity.setText(Integer.toString(quantityCount));

            updateSubtotal((ViewGroup) quantityView.getParent(), quantityCount);
            subtractTotal((ViewGroup) quantityView.getParent(), subtotal);
        }
        else {
            Toast.makeText(this, NO_ITEMS, Toast.LENGTH_SHORT).show();
        }
    }

    public void addItem(View view) {
        Button buttonClicked = findViewById(view.getId());
        ViewGroup quantityView = (ViewGroup) buttonClicked.getParent();
        TextView displayQuantity = (TextView) quantityView.getChildAt(2);

        // Set the quantity and increment
        quantityCount = Integer.parseInt(displayQuantity.getText().toString());
        ++quantityCount;

        // Display the new quantity
        displayQuantity.setText(Integer.toString(quantityCount));

        updateSubtotal((ViewGroup) quantityView.getParent(), quantityCount);
        addTotal((ViewGroup) quantityView.getParent(), subtotal);
    }

    public void updateSubtotal(ViewGroup view, int quantity) {
        ViewGroup subtotalView = (ViewGroup) view.getChildAt(5);
        TextView displaySubtotal = (TextView) subtotalView.getChildAt(1);

        TextView price = getPrice(view);

        subtotal = Double.parseDouble(price.getText().toString());
        subtotal *= quantity;

        displaySubtotal.setText(Double.toString(subtotal));
    }

    public TextView getPrice(ViewGroup view) {
        return (TextView) view.getChildAt(2);
    }

    public void addTotal(ViewGroup view, double subtotal) {
        // Extract the current total and calculate the changes
        TextView displayTotal = getTotal(view);
        total = Double.parseDouble(displayTotal.getText().toString());

        total += subtotal;

        displayTotal.setText(Double.toString(total));

    }

    public void subtractTotal (ViewGroup view, double subtotal) {
        // Extract the current total and calculate the changes
        TextView displayTotal = getTotal(view);
        total = Double.parseDouble(displayTotal.getText().toString());

        total -= subtotal;

        displayTotal.setText(Double.toString(total));
    }

    public TextView getTotal(ViewGroup view) {
        // Move all the way to the view containing the total TextViews
        ViewGroup cardParent = (ViewGroup) view.getParent();
        ViewGroup allCardsParent = (ViewGroup) cardParent.getParent();
        ViewGroup scrollViewParent = (ViewGroup) allCardsParent.getParent();
        ViewGroup menuViewParent = (ViewGroup) scrollViewParent.getParent();
        ViewGroup totalView = (ViewGroup) menuViewParent.getChildAt(0);

        // Extract the current total
        return (TextView) totalView.getChildAt(1);
    }
}
