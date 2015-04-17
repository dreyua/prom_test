package com.example.drey.testforpromua.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drey.testforpromua.OrdersHolder;
import com.example.drey.testforpromua.R;
import com.example.drey.testforpromua.dataobjects.Item;
import com.example.drey.testforpromua.dataobjects.Order;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by drey on 15.04.15.
 */
public class ItemListAdapter extends ArrayAdapter<Item> {
    int layout;
    public ItemListAdapter(Context context, int resource) {
        super(context, resource);
        layout = resource;
    }

    public void setData(List<Item> items){
        clear();
        if (items != null)
            addAll(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView sku = (TextView) v.findViewById(R.id.sku);
        ImageView image = (ImageView) v.findViewById(R.id.image);

        Item i = getItem(position);
        name.setText(i.name);
        price.setText(String.format("%.0f x %.0f %s",i.quantity, i.price, i.currency));
        sku.setText(i.sku);
        ImageLoader.getInstance().displayImage(i.image, image);
        return v;
    }

}
