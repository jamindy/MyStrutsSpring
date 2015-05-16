package com.jamindy.AOP.advisor;

import com.jamindy.AOP.proxy.iface.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 15-5-15.
 */
public class BeanNameAutoProxyClient {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/beannameautoproxyconfig.xml");
        Hello helloProxy = (Hello)context.getBean("myHello");

        helloProxy.sayHi();
        helloProxy.sayGoodbye();
    }
}
