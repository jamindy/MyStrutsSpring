package com.jamindy.AOP.configaspectJ;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-17.
 */
public class Client {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/aspectJconfigIntroduction.xml");
        Hello hello = (Hello)context.getBean("myHello");

        hello.sayHi();
        hello.sayGoodbye();
    }
}
