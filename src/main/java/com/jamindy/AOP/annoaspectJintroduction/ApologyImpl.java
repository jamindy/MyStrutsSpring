package com.jamindy.AOP.annoaspectJintroduction;

import com.jamindy.AOP.introduction.Apology;

/**
 * Created by admin on 15-5-16.
 */
public class ApologyImpl implements Apology{

    @Override
    public void saySorry(String name) {
        System.out.println("sorry,"+name);
    }
}
