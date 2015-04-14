package com.example.drey.testforpromua.dataobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by drey on 14.04.15.
 */
public class Item {
    @Attribute
    Long id;
    @Element
    String name;
    @Element
    Float quantity;
    @Element
    String currency;
    @Element(required = false)
    String image;
    @Element
    String url;
    @Element
    Float price;
    @Element(required = false)
    String sku;
}
