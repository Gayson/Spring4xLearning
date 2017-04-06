package com.gayson.dao;

import com.gayson.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL="SELECT count(*) FROM t_user WHERE user_name=? AND password=?";
    private final static String GET_USER_SQL="SELECT * FROM t_user WHERE user_name=? AND password=?";
    private final static String UPDATE_LOGIN_INFO_SQL="UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";
    private final static String INSERT_USER_SQL="INSERT INTO t_user(user_name, password, credits, last_ip, last_visit) VALUE(?,?,?,?,?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template){
        jdbcTemplate=template;
    }

    public int getMatchCount(String userName, String password){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},int.class);
    }

    public User findByUserName(final String userName, String password){
        final User user=new User();
        jdbcTemplate.query(GET_USER_SQL, new Object[]{userName, password}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet);
                user.setUserID(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void insertUser(User user){
        jdbcTemplate.update(INSERT_USER_SQL,new Object[]{user.getUserName(),user.getPassword()
                ,user.getCredits(),user.getLastIP(),user.getLastVisit()});
    }

    public void updateUserInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIP(),user.getCredits(),user.getUserID()});
    }
}
