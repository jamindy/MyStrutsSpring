package com.jamindy.AOP.annoaspectJintroduction;

import com.jamindy.AOP.introduction.Apology;
import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-16.
 */
public class Client {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/aspectconfig.xml");
        Hello hello = (Hello)context.getBean("myHello");

        hello.sayHi();
        hello.sayGoodbye();

        Apology apology = (Apology)hello;
        apology.saySorry("jamindy");
    }
}
