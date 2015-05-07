package com.jamindy.IOC.autowire;

/**
 * Created by admin on 15-4-29.
 */
public class NoAutoWire implements AutoWire {

    private String type;

    public NoAutoWire(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return "no";
    }
}
