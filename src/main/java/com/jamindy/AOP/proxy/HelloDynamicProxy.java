package com.jamindy.AOP.proxy;

import com.jamindy.AOP.proxy.iface.Hello;
import com.jamindy.AOP.proxy.impl.MyHello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 15-5-15.
 */
public class HelloDynamicProxy implements InvocationHandler {

    private Object target;

    public HelloDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        before();
        Object result = method.invoke(target, objects);
        after();
        return result;
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

    public static void main(String[] args){
        Hello myHello = new MyHello();
        Hello dynamicProxy = new HelloDynamicProxy(myHello).getProxy();
        dynamicProxy.sayHi();
        dynamicProxy.sayGoodbye();
    }
}
