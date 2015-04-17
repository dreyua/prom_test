package com.example.drey.testforpromua;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.drey.testforpromua.adapters.ItemListAdapter;
import com.example.drey.testforpromua.dataobjects.Order;

import java.util.List;

/**
 * Created by drey on 16.04.15.
 */
public class OrderFragment extends Fragment {
    private ItemListAdapter _adapter;
    private ListView _list;

    public OrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_page, container, false);
        if (getArguments().containsKey(MainFragment.TAG_POS)) {
            int pos = getArguments().getInt(MainFragment.TAG_POS);
            List<Order> orders = ((OrdersHolder) getActivity()).getOrders();
            if (orders != null && pos < orders.size()) {
                Order o = orders.get(pos);
                TextView id = (TextView) v.findViewById(R.id.ordernum);
                id.setText(o.id.toString());
                TextView name = (TextView) v.findViewById(R.id.name);
                name.setText(o.name);
                TextView time = (TextView) v.findViewById(R.id.time);
                time.setText(o.date);
                TextView phone = (TextView) v.findViewById(R.id.phone);
                phone.setText(o.phone);
                TextView addr = (TextView) v.findViewById(R.id.address);
                addr.setText(o.address);
                TextView email = (TextView) v.findViewById(R.id.email);
                email.setText(o.email);
                _list = (ListView) v.findViewById(R.id.itemList);
                _adapter = new ItemListAdapter(getActivity().getApplicationContext(), R.layout.item_item);
                _list.setAdapter(_adapter);
                _adapter.setData(o.getItems());
            }
        }
        return v;
    }
}
