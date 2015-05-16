package com.jamindy.AOP.proxy;

import com.jamindy.AOP.proxy.iface.Hello;
import com.jamindy.AOP.proxy.impl.MyHello;

/**
 * Created by admin on 15-5-15.
 */
public class HelloStaticProxy implements Hello {
    private Hello hello;

    public HelloStaticProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public void sayGoodbye() {
        before();
        hello.sayGoodbye();
        after();
    }

    @Override
    public void sayHi() {
        before();
        hello.sayHi();
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

    public static void main(String[] args){
        Hello myhello = new MyHello();
        Hello staticProxy = new HelloStaticProxy(myhello);
        staticProxy.sayHi();
        staticProxy.sayGoodbye();
    }
}
