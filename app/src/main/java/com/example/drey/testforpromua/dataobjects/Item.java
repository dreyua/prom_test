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
    public static final String ITEM_NAME="name";
    public static final String ITEM_SKU="sku";
    @DatabaseField(generatedId = true)
    Long _id;
    @DatabaseField(columnName = "item_id")
    @Attribute
    Long id;
    @DatabaseField(columnName = ITEM_NAME)
    @Element
    public String name;
    @DatabaseField()
    @Element
    public Float quantity;
    @DatabaseField()
    @Element
    public String currency;
    @DatabaseField()
    @Element(required = false)
    public String image;
    @DatabaseField()
    @Element
    public String url;
    @DatabaseField()
    @Element
    public Float price;
    @DatabaseField(columnName = ITEM_SKU)
    @Element(required = false)
    public String sku;
    @DatabaseField(canBeNull = false, foreign = true)
    Order order;
}
