package com.gayson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by jixunzhen on 2017/4/8.
 */

@ContextConfiguration("classpath*:/spring-context.xml")
public class MutiTest extends AbstractTransactionalTestNGSpringContextTests {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    @Rollback(false)
    public void simpleTest() {
        userService.multiConnTest("gayson");
    }
}
