package com.jamindy.AOP.introduction;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-15.
 */
public class IntroductionClient {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/introduceconfig.xml");
        Hello helloProxy = (Hello)context.getBean("helloProxy");

        helloProxy.sayHi();
        helloProxy.sayGoodbye();

        Apology apologyProxy = (Apology)helloProxy;
        apologyProxy.saySorry("jamindy");
    }
}
