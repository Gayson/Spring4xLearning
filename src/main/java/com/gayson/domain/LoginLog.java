package com.gayson.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jixunzhen on 2017/4/5.
 */
public class LoginLog implements Serializable {
    private int loginLogID;
    private int userID;
    private String ip;
    private Date loginDate;

    public int getLoginLogID() {
        return loginLogID;
    }

    public void setLoginLogID(int loginLogID) {
        this.loginLogID = loginLogID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
