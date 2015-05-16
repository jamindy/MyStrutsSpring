package com.jamindy.AOP.aspectJ;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-15.
 */
public class AspectClient {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/aspectconfig.xml");
        Hello hello = (Hello)context.getBean("myHello");

        hello.sayHi();

        hello.sayGoodbye();

    }
}
