package com.example.drey.testforpromua;

import android.app.Fragment;
import android.os.Bundle;

import com.example.drey.testforpromua.dataobjects.Order;

import java.util.List;

/**
 * Created by drey on 17.04.15.
 */
public class RetainedFragment extends Fragment implements OrdersHolder{

    private List<Order> _orders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void setOrders(List<Order> orders) {
        _orders = orders;
    }

    @Override
    public List<Order> getOrders() {
        return _orders;
    }
}
