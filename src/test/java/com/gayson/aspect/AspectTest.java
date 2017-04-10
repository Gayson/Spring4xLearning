package com.gayson.aspect;

import com.gayson.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by jixunzhen on 2017/4/6.
 */
@ContextConfiguration("classpath*:/spring-context.xml")
public class AspectTest extends AbstractTransactionalTestNGSpringContextTests {
    final static Logger logger=LoggerFactory.getLogger(AspectTest.class);
    private Car car;

    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Test
    public void beforeAdviceTest(){
        logger.info(""+car.getMaxSpeed());
        logger.warn("" + car.getBrand());
    }
}
