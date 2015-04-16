package com.example.drey.testforpromua.dataobjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by drey on 14.04.15.
 */
@DatabaseTable(tableName = "items")
public class Item {
    @DatabaseField(generatedId = true)
    Long _id;
    @DatabaseField(columnName = "item_id")
    @Attribute
    Long id;
    @DatabaseField()
    @Element
    String name;
    @DatabaseField()
    @Element
    Float quantity;
    @DatabaseField()
    @Element
    String currency;
    @DatabaseField()
    @Element(required = false)
    String image;
    @DatabaseField()
    @Element
    String url;
    @DatabaseField()
    @Element
    Float price;
    @DatabaseField()
    @Element(required = false)
    String sku;
    @DatabaseField(canBeNull = false, foreign = true)
    Order order;
}
