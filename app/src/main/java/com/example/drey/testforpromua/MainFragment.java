package com.example.drey.testforpromua;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.drey.testforpromua.dataobjects.Feed;
import com.example.drey.testforpromua.util.*;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;

/**
 * Created by drey on 14.04.15.
 */
public class MainFragment extends Fragment {
    ConnectionListener _cl = new ConnectionListener();
    CheckBox _networkStatus;
    TextView _actionStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        _networkStatus = (CheckBox) v.findViewById(R.id.network_status);
        _actionStatus = (TextView) v.findViewById(R.id.action_status);
        return v;
    }


    public class ConnectionListener extends BroadcastReceiver{

        @Override
        public void onReceive( Context context, Intent intent )
        {
            boolean connected = Network.getConnectivityStatus(getActivity().getApplicationContext()) != 0;
            _networkStatus.setChecked(connected);
            if (connected)
                new DownloadTask().execute("https://my.prom.ua/cabinet/export_orders/xml/306906?hash_tag=e1177d00a4ec9b6388c57ce8e85df009");


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
    private class DownloadTask extends AsyncTask<String, Long, File> {
        protected File doInBackground(String... urls) {
            try {
                HttpRequest request =  HttpRequest.get(urls[0]);
                File file = null;
                if (request.ok()) {
                    file = File.createTempFile("download", ".tmp");
                    request.receive(file);
                    publishProgress(file.length());
                }
                return file;
            } catch (HttpRequest.HttpRequestException exception) {
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(Long... progress) {
            _actionStatus.setText("Downloaded bytes: " + progress[0]);
        }

        protected void onPostExecute(File file) {
            if (file != null) {
                _actionStatus.setText("Downloaded file to: " + file.getAbsolutePath());
                Serializer serializer = new Persister();
                Feed f = new Feed();
                try {
                    f = serializer.read(Feed.class, file, false);
                    _actionStatus.append("\nParsed date: " + f.date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                _actionStatus.setText("Download failed");
        }
    }

}


