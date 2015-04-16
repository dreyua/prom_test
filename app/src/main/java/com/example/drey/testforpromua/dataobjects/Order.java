package com.example.drey.testforpromua.dataobjects;


/**
 * Created by drey on 14.04.15.
 */



import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

@DatabaseTable(tableName = "orders")
public class Order {
    @DatabaseField(columnName = "_ID", generatedId = true, allowGeneratedIdInsert = true)
    @Attribute
    public Long id;
    @DatabaseField()
    @Attribute
    String state;
    @DatabaseField()
    @Element
    public String name;
    @DatabaseField()
    @Element
    public String phone;
    @DatabaseField()
    @Element
    public String email;
    @DatabaseField()
    @Element
    public String date;
    @DatabaseField()
    @Element
    public String address;
    @DatabaseField()
    @Element(required = false)
    String index;
    @DatabaseField()
    @Element(required = false)
    String paymentType;
    @DatabaseField()
    @Element(required = false)
    String deliveryType;
    @DatabaseField()
    @Element
    public String priceUAH;
    @ElementList
    List<Item> items;
    @ForeignCollectionField(eager = true)
    ForeignCollection<Item> itemsCollection;
    public String getItemsInfo(){
        String res = "";
        int limit=10;
        for (Item i:itemsCollection){
            if (res!="") res += ", ";
            res += (i.name.length()<=limit?i.name:i.name.substring(0,limit));
        }
        return res;
    }

    public Order(){}

}
