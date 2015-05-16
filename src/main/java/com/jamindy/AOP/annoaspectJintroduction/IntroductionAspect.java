package com.jamindy.AOP.annoaspectJintroduction;

import com.jamindy.AOP.introduction.Apology;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 15-5-16.
 */
@Aspect
@Component
public class IntroductionAspect{

    @DeclareParents(value="com.jamindy.AOP.proxy.impl.MyHello",defaultImpl = ApologyImpl.class)
    public Apology apology;
}
