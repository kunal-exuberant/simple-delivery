package com.example.simple_delivery.simpledelivery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class VegItem extends ArrayAdapter<String>{

    static class ViewHolder{
        TextView tvVegItem;
        ImageView ivVegItem;
        TextView tvVegQty, tvVegPrice, tvVegTotal;
        Button btnQtyDesc, btnQtyInc;
    }

    TextView tvTotalPrice;
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final Integer[] vegPrice;
    private final Integer[] vegQty;
    private final Double[] vegItemSum;
    private double total;

    public VegItem(Activity context, String[] itemname, Integer[] imgid, Integer[] vegPrice, Integer[] vegQty, Double[] vegItemSum) {
        super(context, R.layout.veg_item, itemname);

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
        this.vegPrice  = vegPrice;
        this.vegQty  = vegQty;
        this.vegItemSum  = vegItemSum;
    }

    public View getView(final int position,View view,ViewGroup parent) {

        View vegView = view;
        ViewHolder holder = null;
        tvTotalPrice =  (TextView)context.findViewById(R.id.tvTotalPrice);
        if(vegView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            vegView = inflater.inflate(R.layout.veg_item, null, true);
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
        tvTotalPrice.setText("Rs "+ PickVegetables.totalPrice);

        holder.btnQtyInc.setTag(holder);
        holder.btnQtyInc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder tagHolder = (ViewHolder) v.getTag();
                int qty = Integer.parseInt(tagHolder.tvVegQty.getText().toString()) + 1;
                if(qty > AppProperties.VEG_QTY_MAX) {
                    ;
                }else{
                    double price = Integer.parseInt(tagHolder.tvVegPrice.getTag().toString());
                    tagHolder.tvVegQty.setText(qty + "");
                    vegQty[position] = qty;
                    tagHolder.tvVegTotal.setText(qty*price+"");
                    vegItemSum[position] = qty*price;
                    total = 0.0;
                    for(int i=0; i<vegItemSum.length; i++){
                        total += vegItemSum[i];
                    }
                    tvTotalPrice.setText("Rs "+total);
                    PickVegetables.totalPrice = total;
                }
            }
        });

        holder.btnQtyDesc.setTag(holder);
        holder.btnQtyDesc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder tagHolder = (ViewHolder) v.getTag();
                int qty = Integer.parseInt(tagHolder.tvVegQty.getText().toString()) - 1;
                if(qty < AppProperties.VEG_QTY_MIN) {
                    ;
                }else{
                    double price = Integer.parseInt(tagHolder.tvVegPrice.getTag().toString());
                    tagHolder.tvVegQty.setText(qty+"");
                    vegQty[position] = qty;
                    tagHolder.tvVegTotal.setText(qty*price+"");
                    vegItemSum[position] = qty*price;
                    total = 0.0;
                    for(int i=0; i<vegItemSum.length; i++){
                        total += vegItemSum[i];
                    }
                    tvTotalPrice.setText("Rs "+total);
                    PickVegetables.totalPrice = total;
                }
            }
        });

        return vegView;
    };
}