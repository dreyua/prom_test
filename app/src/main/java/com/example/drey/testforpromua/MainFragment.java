package com.example.drey.testforpromua;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.drey.testforpromua.dataobjects.Feed;
import com.example.drey.testforpromua.adapters.OrderListAdapter;
import com.example.drey.testforpromua.dataobjects.Order;
import com.example.drey.testforpromua.orm.HelperFactory;
import com.example.drey.testforpromua.orm.OrderDAO;
import com.example.drey.testforpromua.util.*;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by drey on 14.04.15.
 */
public class MainFragment extends Fragment {
    private final String FEED_URL="https://my.prom.ua/cabinet/export_orders/xml/306906?hash_tag=e1177d00a4ec9b6388c57ce8e85df009";
    ConnectionListener _cl = new ConnectionListener();
    CheckBox _networkStatus;
    TextView _actionStatus;
    ListView _orders;
    OrderListAdapter _adapter;
    SwipeRefreshLayout _refresh;
    DownloadTask _dTask;
    List<Order> _ordersList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _adapter = new OrderListAdapter(getActivity().getApplicationContext(), R.layout.item_order);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        _orders = (ListView) v.findViewById(R.id.orderList);
        _orders.setAdapter(_adapter);
        _adapter.setData((OrdersHolder)getActivity());
        _orders.setEmptyView(v.findViewById(R.id.empty));
        _orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                Fragment f = new OrdersPagerFragment();
                Bundle b = new Bundle();
                b.putInt("pos", position);
                f.setArguments(b);
                tr.replace(R.id.main_container, f).addToBackStack("pager").commit();
            }
        });
        _networkStatus = (CheckBox) v.findViewById(R.id.network_status);
        _actionStatus = (TextView) v.findViewById(R.id.action_status);
        _refresh = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        _refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _actionStatus.setText("Starting update...\n");
                if (_dTask != null)
                    _dTask.cancel(true);
                _dTask = new DownloadTask();
                _dTask.execute(FEED_URL);
            }
        });
        return v;
    }


    public class ConnectionListener extends BroadcastReceiver{

        @Override
        public void onReceive( Context context, Intent intent )
        {
            boolean connected = Network.getConnectivityStatus(getActivity().getApplicationContext()) != 0;
            _networkStatus.setChecked(connected);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getApplicationContext().registerReceiver(_cl, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getApplicationContext().unregisterReceiver(_cl);
    }
    private class DownloadTask extends AsyncTask<String, String, Feed> {
        protected Feed doInBackground(String... urls) {
            try {
                publishProgress("Performing HTTP request...");
                HttpRequest request =  HttpRequest.get(urls[0]);
                File file = null;
                Feed f = new Feed();
                if (_networkStatus.isChecked() && request.ok()) {
                    publishProgress(" OK\n");
                    file = File.createTempFile("download", ".tmp");
                    request.receive(file);
                    publishProgress("Downloaded bytes: "+file.length()+"\n");
                    Serializer serializer = new Persister();
                    f = serializer.read(Feed.class, file, false);
                    publishProgress("Parsed orders: "+ (f.getOrderCount())+"\n");
                    if (f!= null) {
                        f.saveToDB();
                    }
                } else {
                    publishProgress(" FAIL\n");
                    f.loadFromDB();
                    publishProgress("Orders loaded from local storage: " + f.getOrderCount() + "\n");
                }
                return f;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(String... msg) {
            _actionStatus.append(msg[0]);
        }

        protected void onPostExecute(Feed feed) {
            _actionStatus.append("Update complete\n");
            _refresh.setRefreshing(false);
                OrdersHolder oh = (OrdersHolder) getActivity();
                oh.setOrders(HelperFactory.getHelper().getOrderDAO().getAllOrders());
                _adapter.setData(oh);
                _ordersList = oh.getOrders();

        }
    }

}


