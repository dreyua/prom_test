package com.example.drey.testforpromua.dataobjects;

/**
 * Created by drey on 14.04.15.
 */

import com.example.drey.testforpromua.orm.HelperFactory;
import com.example.drey.testforpromua.orm.ItemDAO;
import com.example.drey.testforpromua.orm.OrderDAO;
import com.j256.ormlite.stmt.DeleteBuilder;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Root
public class Feed {
    @Attribute(name = "date")
    public String date;
    @ElementList(inline = true)
    public List<Order> orders;

    public int getOrderCount() {
        return (orders == null ? 0 : orders.size());
    }

    public void saveToDB() {
        if (getOrderCount() > 0) {
            OrderDAO orders = HelperFactory.getHelper().getOrderDAO();
            ItemDAO items = HelperFactory.getHelper().getItemDAO();
            if (orders != null && items != null) {
                try {
                    items.deleteBuilder().delete();
                    orders.deleteBuilder().delete();
                    for (Order o : this.orders) {
                        orders.createOrUpdate(o);
                        DeleteBuilder d = items.deleteBuilder();
                        d.where().eq("order_id", o.id);
                        d.delete();
                        for (Item i : o.items) {
                            i.order = o;
                            items.createOrUpdate(i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadFromDB() {
        orders = null;
        OrderDAO table = HelperFactory.getHelper().getOrderDAO();
        if (table != null){
                orders = table.getAllOrders();
        }
    }
}