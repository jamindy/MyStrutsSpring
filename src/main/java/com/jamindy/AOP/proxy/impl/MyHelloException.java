package com.jamindy.AOP.proxy.impl;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 15-5-15.
 */
@Component
public class MyHelloException implements Hello {

    @Override
    public void sayHi() {
        System.out.println("hi~~");

        throw new RuntimeException("Exception");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("goodbye~~");
    }
}
