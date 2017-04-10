package com.gayson.utils;

import com.gayson.domain.Forum;
import com.gayson.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by jixunzhen on 2017/4/8.
 */

@ContextConfiguration("classpath*:/spring-context.xml")
public class IncrementerTest extends AbstractTransactionalTestNGSpringContextTests {
    MySQLFieldIncrementer incrementer;

    @Autowired
    public void setIncrementer(MySQLFieldIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Test
    public void insertNewNameTest() {
        logger.info(incrementer.insertNewTable(User.class));
    }

    @Test
    @Rollback(false)
    public void incrementerTest() {
        incrementer.insertNewTable(Forum.class);

        for (int i = 0; i < 30; i++) {
            System.out.println(incrementer.getNextKey(User.class));
        }
        for (int i = 0; i < 30; i++) {
            System.out.println(incrementer.getNextKey(Forum.class));
        }
        for (int i = 0; i < 30; i++) {
            System.out.println(incrementer.getNextKey(User.class));
        }
        for (int i = 0; i < 30; i++) {
            System.out.println(incrementer.getNextKey(Forum.class));
        }
    }
}
