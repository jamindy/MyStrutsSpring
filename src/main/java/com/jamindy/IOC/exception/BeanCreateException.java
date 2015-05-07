package com.jamindy.IOC.exception;

/**
 * Created by admin on 15-4-28.
 */
public class BeanCreateException extends RuntimeException {

    public BeanCreateException(){

    }

    public BeanCreateException(String message) {
        super(message);
    }

    public BeanCreateException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BeanCreateException(Throwable throwable) {
        super(throwable);
    }
}
