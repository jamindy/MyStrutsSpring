package com.jamindy.IOC.element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-4-29.
 */
public class CollectionElement implements LeafElement {

    private String type;
    private List<LeafElement> list;

    public CollectionElement(String type) {
        this.type = type;

    }

    public void setList(List<LeafElement> list) {
        this.list = list;
    }

    public void add(LeafElement leafElement) {
        this.list.add(leafElement);
    }

    @Override
    public String getType() {
        return this.type;
    }

    public List<LeafElement> getList() {
        return list;
    }

    @Override
    public Object[] getValue() {
        List<Object> value = new ArrayList<Object>();
        for (LeafElement le : this.getList()) {
            value.add(le.getValue());
        }
        return value.toArray();
    }
}
