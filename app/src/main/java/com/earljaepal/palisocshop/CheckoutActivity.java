package com.earljaepal.palisocshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckoutActivity extends AppCompatActivity {
    private static final double TPS = 0.05;
    private static final double TVQ = 0.09975;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        String stringTotal = (intent.getStringExtra(MenuActivity.EXTRA_MESSAGE)).substring(1);
        TextView setTotal = findViewById(R.id.pretotal);
        setTotal.setText("$" + stringTotal);

        calculateTotal(stringTotal);

    }

    public void calculateTotal(String stringTotal) {
        double beforeTax;
        double afterTax;

        beforeTax = Double.parseDouble(stringTotal);
        afterTax = beforeTax + calculateTPS(beforeTax) + calculateTVQ(beforeTax);

        TextView finalTotalText = findViewById(R.id.total);
        finalTotalText.setText(String.format("$%.2f", afterTax));
    }

    public double calculateTPS(double beforeTotal) {
        double tax = beforeTotal * TPS;
        TextView setTPS = findViewById(R.id.tps);
        setTPS.setText(String.format("%.2f", tax));
        return tax;
    }

    private double calculateTVQ(double beforeTotal) {
        double tax = beforeTotal * TVQ;
        TextView setTPS = findViewById(R.id.tvq);
        setTPS.setText(String.format("%.2f", tax));
        return tax;
    }
}
