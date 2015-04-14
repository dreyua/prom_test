package com.example.drey.testforpromua.dataobjects;


/**
 * Created by drey on 14.04.15.
 */

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Order {
    @Attribute
    Long id;
    @Attribute
    String state;
    @Element
    String name;
    @Element
    String phone;
    @Element
    String email;
    @Element
    String date;
    @Element
    String address;
    @Element(required = false)
    String index;
    @Element(required = false)
    String paymentType;
    @Element(required = false)
    String deliveryType;
    @Element
    String priceUAH;
    @ElementList
    List<Item> items;

}
