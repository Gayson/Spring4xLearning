package com.gayson.dao;

import com.gayson.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@Repository
public class LoginLogDao {
    private final static String INSERT_LOGIN_LOG_SQL="INSERT INTO t_login_log(user_id, ip, login_datetime) VALUE (?,?,?)";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template){
        jdbcTemplate=template;
    }

    public void insertLoginLog(LoginLog loginLog){
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, loginLog.getUserID(), loginLog.getIp(), loginLog.getLoginDate());
    }
}
