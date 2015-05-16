package com.jamindy.AOP.aspectJaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 15-5-15.
 */
@Aspect
@Component
public class HelloAspect {

    @Around("execution(* com.jamindy.AOP.proxy.impl.MyHello.sayG*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}
