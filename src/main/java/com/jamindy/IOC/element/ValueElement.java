package com.jamindy.IOC.element;

/**
 * Created by admin on 15-4-29.
 */
public class ValueElement implements LeafElement {

    private Object value;


    public ValueElement(Object value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "value";
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
