package com.jamindy.AOP.proxy;

import com.jamindy.AOP.proxy.iface.Hello;
import com.jamindy.AOP.proxy.impl.MyHello;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by admin on 15-5-15.
 */
public class HelloCglibProxy implements MethodInterceptor {

    private static HelloCglibProxy instance = new HelloCglibProxy();

    public HelloCglibProxy() {
    }

    public static HelloCglibProxy getInstance(){
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(target,args);
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
        Hello helloProxy = HelloCglibProxy.getInstance().getProxy(MyHello.class);
        helloProxy.sayHi();
        helloProxy.sayGoodbye();
    }
}
