package com.gayson.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by jixunzhen on 2017/4/6.
 */
public class SimpleAspect {
    public void beforeMethod(JoinPoint point){
        System.out.println("before advice-----------------");
        System.out.println(point.getTarget());
    }

    public void atferMethod(){
        System.out.println("after advice------------------");
    }

    public void aroundMethod(ProceedingJoinPoint pjp){
        System.out.println("begin around advice-----------");
        System.out.println(pjp.getTarget());
        System.out.println("after around advice-----------");
    }
}
