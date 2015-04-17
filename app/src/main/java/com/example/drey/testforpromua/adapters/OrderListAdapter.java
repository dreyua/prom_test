package com.example.drey.testforpromua.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.drey.testforpromua.OrdersHolder;
import com.example.drey.testforpromua.R;
import com.example.drey.testforpromua.dataobjects.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by drey on 15.04.15.
 */


public class OrderListAdapter extends ArrayAdapter<Order> {
    int layout;
    public OrderListAdapter(Context context, int resource) {
        super(context, resource);
        layout = resource;
    }

    public void setData(OrdersHolder oh){
        clear();
        if (oh.getOrders() != null)
            addAll(oh.getOrders());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(layout, parent, false);
            TextView num = (TextView) v.findViewById(R.id.ordernum);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView time = (TextView) v.findViewById(R.id.time);
        TextView content = (TextView) v.findViewById(R.id.content);
        TextView price = (TextView) v.findViewById(R.id.total);

        Order o = getItem(position);
        num.setText(o.id.toString());
        name.setText(o.name);
        time.setText(o.date);
        content.setText(o.getItemsInfo());
        price.setText(o.priceUAH);
        return v;
    }

}
