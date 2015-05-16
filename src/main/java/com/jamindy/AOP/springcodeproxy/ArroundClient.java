package com.jamindy.AOP.springcodeproxy;

import com.jamindy.AOP.proxy.iface.Hello;
import com.jamindy.AOP.proxy.impl.MyHello;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by admin on 15-5-15.
 */
public class ArroundClient {

    public static void main(String[] args){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new MyHello());
        proxyFactory.addAdvice(new HelloArroundAdvice());

        Hello helloProxy = (Hello)proxyFactory.getProxy();
        helloProxy.sayHi();
        helloProxy.sayGoodbye();
    }
}
