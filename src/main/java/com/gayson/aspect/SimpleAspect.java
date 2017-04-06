package com.gayson.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class SimpleAspect {
    public void beforeMethod(JoinPoint point){
        System.out.println("before advice-----------------");
        System.out.println(point.getTarget());
    }

    public void afterMethod(int val) {
        System.out.println("after advice------------------");
        System.out.println(val);
    }

    public void aroundMethod(ProceedingJoinPoint pjp){
        System.out.println("begin around advice-----------");
        System.out.println(pjp.getTarget());
        System.out.println("after around advice-----------");
    }
}
