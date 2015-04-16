package com.example.drey.testforpromua;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drey.testforpromua.adapters.OrderPagerAdapter;

/**
 * Created by drey on 14.04.15.
 */
public class OrdersPagerFragment extends Fragment {

    private ViewPager _pager;
    OrderPagerAdapter _adapter;


    public OrdersPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_pager, container, false);
        _pager = (ViewPager) v.findViewById(R.id.pager);
        _adapter = new OrderPagerAdapter(getFragmentManager());
        _pager.setAdapter(_adapter);
        if (getActivity() instanceof  OrdersHolder) {
            _adapter.setData((OrdersHolder) getActivity());
            Bundle args = getArguments();
            _pager.setCurrentItem(args.getInt("pos", 0));
        }
        return v;
    }


}
