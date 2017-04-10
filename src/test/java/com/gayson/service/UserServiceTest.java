package com.gayson.service;

import com.gayson.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@ContextConfiguration("classpath*:/spring-context.xml")
@Rollback(false)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    private UserService userService;

    private static void assertTrue(boolean b) {
        if (b) {
            System.out.println("**********TRUE***********");
        } else System.out.println("**********FALSE**********");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void insertUser(){
        User user=new User();
        user.setUserName("hello");
        user.setPassword("123456");
        user.setCredits(0);
        user.setLastIP("192.168.0.1");
        user.setLastVisit(new Date());

        userService.saveUser(user);
    }

    @Test
    public void removeUser() {
        User user = new User();
        //user.setUserID(61);
        userService.deleteUser(user);
    }

    @Test
    public void hasMatchUser(){
        boolean b1=userService.hasMatchUser("gayson","123456");
        boolean b2=userService.hasMatchUser("gayso","123456");
        assertTrue(b1);
        assertTrue(b2);
    }

    @Test
    public void findByUserName(){
        User user=userService.findByUserName("gayson","123456");
        assertTrue(user.getPassword().equals("123456"));
    }

    @Test
    public void login(){
        User user=userService.findByUserName("gayson","123456");
        userService.loginSuccess(user);
        user=userService.findByUserName("gayson","123456");
        System.out.println(user.getCredits());
    }

    @Test
    public void cacheTest() {
        User user = userService.findByUserName("gayson", "123456");

        user = userService.getUserById(user.getUserID());
        user = userService.getUserById(user.getUserID());
        System.out.println(user.getUserName());
    }
}
