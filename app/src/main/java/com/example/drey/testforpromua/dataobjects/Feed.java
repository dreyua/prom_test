package com.example.drey.testforpromua.dataobjects;

/**
 * Created by drey on 14.04.15.
 */
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Feed {
    @Attribute(name="date")
    public String date;
    @ElementList(inline = true)
    List<Order> orders;
}
