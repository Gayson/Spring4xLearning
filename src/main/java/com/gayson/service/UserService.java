package com.gayson.service;

import com.gayson.dao.LoginLogDao;
import com.gayson.dao.UserDao;
import com.gayson.domain.LoginLog;
import com.gayson.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao=loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password){
        int matchCount=userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

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
        userDao.insertUser(user);
    }
}
