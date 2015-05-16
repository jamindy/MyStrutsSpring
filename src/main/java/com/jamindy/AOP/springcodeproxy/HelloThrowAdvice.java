package com.jamindy.AOP.springcodeproxy;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by admin on 15-5-15.
 */
@Component
public class HelloThrowAdvice implements ThrowsAdvice{

      public void afterThrowing(Method method,Object[] args,Object target,Exception e){
          System.out.println("---------- Throw Exception ----------");
          System.out.println("Target Class: " + target.getClass().getName());
          System.out.println("Method Name: " + method.getName());
          System.out.println("Exception Message: " + e.getMessage());
          System.out.println("-------------------------------------");
      }
}
