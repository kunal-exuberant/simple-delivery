package com.example.simple_delivery.simpledelivery;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DropVegItem extends ArrayAdapter<String> {

    static class ViewHolder{
        TextView tvVegItem;
        ImageView ivVegItem;
        TextView tvVegQty, tvVegPrice, tvVegTotal;
        Button btnQtyDesc, btnQtyInc;
    }

    TextView tvTotalPrice;
    private Integer[] quantity;
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final Integer[] vegPrice;
    private final Integer[] vegQty;
    private final Double[] vegItemSum;
    public static Double totalPrice=0.0;

    public DropVegItem(Activity context, String[] itemname, Integer[] imgid, Integer[] vegPrice, Integer[] vegQty, Double[] vegItemSum) {
        super(context, R.layout.veg_item, itemname);

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
        this.vegPrice  = vegPrice;
        this.vegQty  = vegQty;
        this.vegItemSum  = vegItemSum;
    }

    public View getView(int position,View view,ViewGroup parent) {


        View vegView = view;
        ViewHolder holder = null;
        tvTotalPrice =  (TextView)context.findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("Rs "+PickVegetables.totalPrice+"");
        if(vegView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            vegView = inflater.inflate(R.layout.drop_veg_item, null, true);
            holder = new ViewHolder();
            holder.tvVegItem = (TextView) vegView.findViewById(R.id.tvVegItem);
            holder.ivVegItem = (ImageView) vegView.findViewById(R.id.ivVegItem);
            holder.tvVegQty = (TextView) vegView.findViewById(R.id.tvVegQty);
            holder.tvVegPrice = (TextView) vegView.findViewById(R.id.tvVegPrice);
            holder.tvVegTotal = (TextView) vegView.findViewById(R.id.tvVegTotal);
            holder.btnQtyDesc = (Button) vegView.findViewById(R.id.btnQtyDesc);
            holder.btnQtyInc = (Button) vegView.findViewById(R.id.btnQtyInc);

            holder.tvVegItem.setText(itemname[position]);
            holder.ivVegItem.setImageResource(imgid[position]);
            holder.tvVegPrice.setText("Rs " + vegPrice[position] + "/kg");
            holder.tvVegPrice.setTag(vegPrice[position] + "");
            holder.tvVegTotal.setText(vegItemSum[position]+"");
            holder.tvVegQty.setText(vegQty[position]+"");
            vegView.setTag(holder);
        }
        else{
            holder = (ViewHolder)vegView.getTag();
            holder.tvVegItem.setText(itemname[position]);
            holder.ivVegItem.setImageResource(imgid[position]);
            holder.tvVegPrice.setText("Rs " + vegPrice[position] + "/kg");
            holder.tvVegPrice.setTag(vegPrice[position] + "");
            holder.tvVegTotal.setText(vegItemSum[position] + "");
            holder.tvVegQty.setText(vegQty[position] + "");
        }
        return vegView;
    };
}