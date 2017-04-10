package com.gayson.service;

import com.gayson.dao.LoginLogDao;
import com.gayson.dao.UserDao;
import com.gayson.domain.LoginLog;
import com.gayson.domain.User;
import com.gayson.utils.MySQLFieldIncrementer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;
    private HibernateTemplate hibernateTemplate;
    private ScoreService scoreService;
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private MySQLFieldIncrementer incrementer = new MySQLFieldIncrementer();

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao=loginLogDao;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Autowired
    public void setIncrementer(MySQLFieldIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Transactional(readOnly = true)
    public boolean hasMatchUser(String userName, String password){
        int matchCount=userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName, String password){
        return userDao.findByUserName(userName, password);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog=new LoginLog();
        loginLog.setUserID(user.getUserID());
        loginLog.setIp(user.getLastIP());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateUserInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

    public void insertUser(User user){
        long id = incrementer.getNextKey(User.class);
        user.setUserID((int) id);
        userDao.insertUser(user);
    }

    public void saveUser(User user) {
        long id = incrementer.getNextKey(User.class);
        user.setUserID((int) id);
        userDao.save(user);
    }

    public void multiConnTest(String userName) {
        User user = (User) hibernateTemplate.find("from User where user_name=?", userName).get(0);
        user.setLastVisit(new Date());
        hibernateTemplate.update(user);

        hibernateTemplate.flush();

        scoreService.addScore(userName, 20);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
