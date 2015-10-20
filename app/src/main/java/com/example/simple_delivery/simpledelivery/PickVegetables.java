package com.example.simple_delivery.simpledelivery;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class PickVegetables extends Activity implements OnClickListener{

    private ListView lvVegList;
    private TextView tvVegItem, tvTotalPrice;
    private Button btnOrder, btnOrderPlace;
    public static String[] vegList = {"Onion", "Potato", "Tomato", "Capcicum", "Cabbage", "Bitter gourd", "Lady Finger", "Bottle gourd", "Spinach", "Cauli Flower"};
    public static Integer[] imgid = { R.drawable.onion, R.drawable.potato, R.drawable.tomato, R.drawable.capcicum, R.drawable.cabbage, R.drawable.bitter_gourd, R.drawable.lady_finger, R.drawable.bottle_gourd, R.drawable.spinach, R.drawable.cauli_flower};
    public static Integer[] vegQty =   {0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
    public static Integer[] vegPrice = {20, 30, 40, 10, 30, 21, 26, 8, 53, 10};
    public static Double[] vegItemSum = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double totalPrice = 0.0;

    public static String mobile;
    public static String name;
    public static String email;
    public static String address;
    public static String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_vegetables);
        tvTotalPrice = (TextView)findViewById(R.id.btnOrder);
        btnOrder = (Button)findViewById(R.id.btnOrder);
        btnOrderPlace = (Button)findViewById(R.id.btnOrderPlace);

        for(int i=0; i<vegQty.length; i++){
            vegItemSum[i] = (double)(vegQty[i] * vegPrice[i]);
        }

        totalPrice = 0.0;
        for(int i=0; i<vegItemSum.length; i++){
            totalPrice += vegItemSum[i];
        }

        VegItem vegAdapter = new VegItem(this, vegList, imgid, vegPrice, vegQty, vegItemSum);

        lvVegList = (ListView)findViewById(android.R.id.list);

        lvVegList.setAdapter(vegAdapter);


        btnOrder.setOnClickListener(this);
        btnOrderPlace.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnOrder:
            case R.id.btnOrderPlace:
                if(totalPrice > 0.0) {
                    Intent placeOrder = new Intent(getApplicationContext(), DeliveryAddress.class);
                    startActivity(placeOrder);
                }
                else{

                }
                break;
        }

    }
}