package com.example.drey.testforpromua;



import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import com.example.drey.testforpromua.dataobjects.Order;

import java.util.List;

public class MainActivity extends Activity implements OrdersHolder {

    private boolean _showSplash = true;
    private String SHOW_TAG = "showSplash";
    private String DATA_FRAG = "dataFragment";
    private RetainedFragment _rf;

    public List<Order> getOrders() {
        return _rf.getOrders();
    }

    public void setOrders(List<Order> orders) {
        _rf.setOrders(orders);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _rf = (RetainedFragment) getFragmentManager().findFragmentByTag(DATA_FRAG);
        if (_rf == null) {
            _rf = new RetainedFragment();
            getFragmentManager().beginTransaction().add(_rf, DATA_FRAG).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (_showSplash) {
            _showSplash = false;
            FragmentTransaction tr = getFragmentManager().beginTransaction();
            tr.setCustomAnimations(R.animator.rotation, R.animator.rotation);
            tr.add(R.id.main_container, new SplashFragment()).commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null){
            outState.putBoolean(SHOW_TAG, _showSplash);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            _showSplash = savedInstanceState.getBoolean(SHOW_TAG, true);
        }
    }
}
