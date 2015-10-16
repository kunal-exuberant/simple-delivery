package com.example.simple_delivery.simpledelivery;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddress extends Activity implements OnClickListener {

    private ListView lvVegList;
    private TextView tvVegItem;
    private Button btnSubmitAddress, btnSubmit;
    private EditText etName, etAddress, etMobile, etEmail;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_address);
        initView();
        //renderView();
        etMobile.setText(PickVegetables.mobile);
        btnSubmitAddress.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void initView(){
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnSubmitAddress = (Button)findViewById(R.id.btnSubmitAddress);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmitAddress:
                new ProfileFetch().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case R.id.btnSubmit:
                new ProfileFetch().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
        }
    }

    public class ProfileFetch extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            pdialog = new ProgressDialog(DeliveryAddress.this);
            pdialog.setMessage("Submitting Address...");
            pdialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                List<NameValuePair> apiParams = new ArrayList<NameValuePair>();

                apiParams.add(new BasicNameValuePair(AppProperties.ACTION, "delivery_address_submit"));
                apiParams.add(new BasicNameValuePair(AppProperties.USERID, userid));
                /*
                result = CommonMethods.loadJSONData(AppProperties.URL, AppProperties.METHOD_GET, apiParams);
                if (result != null) {
                    data = result.getJSONObject(0);
                    Log.i("data", data.toString());
                }
                */
                Intent placeOrder = new Intent(getApplicationContext(), DropVegetables.class);
                startActivity(placeOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            if (pdialog.isShowing()) {
                pdialog.dismiss();
            }

        }
    }
}