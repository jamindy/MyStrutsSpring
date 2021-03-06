package com.jamindy.AOP.springxmlproxy;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-15.
 */
public class AopXmlClient {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/aopconfig.xml");
        Hello helloProxy = (Hello)context.getBean("helloProxy");

        helloProxy.sayHi();
        helloProxy.sayGoodbye();
    }
}
