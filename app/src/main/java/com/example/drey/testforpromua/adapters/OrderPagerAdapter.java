package com.example.drey.testforpromua.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.drey.testforpromua.OrderFragment;
import com.example.drey.testforpromua.OrdersHolder;
import com.example.drey.testforpromua.dataobjects.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drey on 16.04.15.
 */
public class OrderPagerAdapter extends FragmentStatePagerAdapter {

    List<Order> orders;

    public OrderPagerAdapter (FragmentManager fm){
        super(fm);
        orders = new ArrayList<Order>();
    }

    public void setData(OrdersHolder oh){
        orders = oh.getOrders();
        notifyDataSetChanged();
    }

    public List<Order> getOrders(){
        return orders;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new OrderFragment();
        Bundle args = new Bundle();
        args.putInt("pos", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public int getCount() {
        return orders.size();
    }
}


