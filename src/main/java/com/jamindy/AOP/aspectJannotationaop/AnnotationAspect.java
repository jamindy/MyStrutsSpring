package com.jamindy.AOP.aspectJannotationaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * Created by admin on 15-5-16.
 */
@Aspect
@Component
public class AnnotationAspect {

//    @Before：前置增强
//
//    @After：后置增强
//
//    @Around：环绕增强
//
//    @AfterThrowing：抛出增强
//
//    @DeclareParents：引入增强

    @Around("@annotation(com.jamindy.AOP.aspectJannotationaop.Tag)")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable{
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
