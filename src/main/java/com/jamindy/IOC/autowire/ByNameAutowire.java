package com.jamindy.IOC.autowire;

/**
 * Created by admin on 15-4-29.
 */
public class ByNameAutowire implements AutoWire {
    private String type;

    public ByNameAutowire(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

}
