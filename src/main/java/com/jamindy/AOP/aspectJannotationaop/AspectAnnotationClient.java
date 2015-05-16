package com.jamindy.AOP.aspectJannotationaop;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-16.
 */
public class AspectAnnotationClient {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/aspectconfig.xml");
        Hello hello = (Hello)context.getBean("annotationHello");

        hello.sayHi();

        hello.sayGoodbye();
    }
}
