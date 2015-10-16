package com.example.simple_delivery.simpledelivery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DropVegetables extends Activity implements OnClickListener{

    ListView lvVegList;
    TextView tvVegItem;
    Button btnOrderCancel, btnOrderChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_vegetables);

        btnOrderCancel = (Button)findViewById(R.id.btnOrderCancel);
        btnOrderChange = (Button)findViewById(R.id.btnOrderChange);

        lvVegList = (ListView)findViewById(R.id.lvDropVeg);
        DropVegItem vegAdapter = new DropVegItem(this, PickVegetables.vegList, PickVegetables.imgid, PickVegetables.vegPrice, PickVegetables.vegQty, PickVegetables.vegItemSum);

        lvVegList.setAdapter(vegAdapter);
        btnOrderCancel.setOnClickListener(this);
        btnOrderChange.setOnClickListener(this);
    }

    public void onClick(View v) {
        ProgressDialog pdialog;
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnOrderChange:
                Intent changeOrder = new Intent(getApplicationContext(), PickVegetables.class);
                startActivity(changeOrder);
                break;
            case R.id.btnOrderCancel:
                pdialog = new ProgressDialog(DropVegetables.this);
                pdialog.setMessage("Cancelling order...");
                pdialog.show();
               // Intent placeOrder = new Intent(getApplicationContext(), PickVegetables.class);
               // startActivity(placeOrder);
                break;
        }

    }
}