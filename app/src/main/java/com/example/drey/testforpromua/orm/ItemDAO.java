package com.example.drey.testforpromua.orm;

import com.example.drey.testforpromua.dataobjects.Item;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by drey on 16.04.15.
 */
public class ItemDAO extends BaseDaoImpl<Item, Long> {

protected ItemDAO(ConnectionSource connectionSource,
        Class<Item> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        }

public List<Item> getAllOrders() throws SQLException{
        return this.queryForAll();
        }
}
