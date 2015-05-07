package com.jamindy.IOC.element;

/**
 * Created by admin on 15-4-29.
 */
public class RefElement implements LeafElement {

    private Object value;

    public RefElement(Object value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "ref";
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
