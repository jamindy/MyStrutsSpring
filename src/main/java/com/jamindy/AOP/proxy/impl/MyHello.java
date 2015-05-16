package com.jamindy.AOP.proxy.impl;

import com.jamindy.AOP.aspectJannotation.Tag;
import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 15-5-7.
 */
@Component
public class MyHello implements Hello {

    @Override
    public void sayHi() {
        System.out.println("hi~~");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("goodbye~~");
    }
}
