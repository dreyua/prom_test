package com.example.drey.testforpromua.orm;

import com.example.drey.testforpromua.dataobjects.Order;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by drey on 16.04.15.
 */
public class OrderDAO extends BaseDaoImpl<Order, Long> {

    protected OrderDAO(ConnectionSource connectionSource,
                      Class<Order> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Order> getAllOrders(){
        try {
            return this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
