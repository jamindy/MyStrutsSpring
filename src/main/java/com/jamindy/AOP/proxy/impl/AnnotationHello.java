package com.jamindy.AOP.proxy.impl;

import com.jamindy.AOP.aspectJannotationaop.Tag;
import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 15-5-16.
 */
@Component
public class AnnotationHello implements Hello{

    @Tag
    @Override
    public void sayHi() {
        System.out.println("hi~~");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("goodbye~~");
    }
}
