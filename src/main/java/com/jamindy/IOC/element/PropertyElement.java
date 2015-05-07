package com.jamindy.IOC.element;

/**
 * Created by admin on 15-4-29.
 */
public class PropertyElement {

    private String name;

    private LeafElement leafElement;

    public PropertyElement(String name, LeafElement leafElement) {
        this.leafElement = leafElement;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeafElement getLeafElement() {
        return leafElement;
    }

    public void setLeafElement(LeafElement leafElement) {
        this.leafElement = leafElement;
    }
}
