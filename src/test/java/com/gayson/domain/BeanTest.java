package com.gayson.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by jixunzhen on 2017/4/5.
 */

@ContextConfiguration("classpath*:/spring-context.xml")
public class BeanTest extends AbstractTransactionalTestNGSpringContextTests {
    ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/spring-context.xml");
    @Test
    public void factoryBeanTest(){
        Car benzCar=context.getBean("benzCar",Car.class);
        System.out.println(benzCar);
    }
}