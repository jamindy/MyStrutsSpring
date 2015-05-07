package com.jamindy.IOC.exception;

/**
 * Created by admin on 15-4-30.
 */
public class PropertyException extends RuntimeException {

    public PropertyException() {
    }

    public PropertyException(String s) {
        super(s);
    }

    public PropertyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PropertyException(Throwable throwable) {
        super(throwable);
    }
}
