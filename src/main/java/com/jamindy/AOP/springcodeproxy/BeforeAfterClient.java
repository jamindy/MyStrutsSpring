package com.jamindy.AOP.springcodeproxy;

import com.jamindy.AOP.proxy.iface.Hello;
import com.jamindy.AOP.proxy.impl.MyHello;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by admin on 15-5-15.
 */
public class BeforeAfterClient {

    public static void main(String[] args){
        Hello hello = new MyHello();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(hello);
        proxyFactory.addAdvice(new HelloBeforeAdvice());
        proxyFactory.addAdvice(new HelloAfterAdvice());

        Hello helloProxy = (Hello)proxyFactory.getProxy();
        helloProxy.sayHi();
        helloProxy.sayGoodbye();
    }
}
