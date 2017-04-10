package com.gayson.dao;

import com.gayson.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jixunzhen on 2017/4/5.
 */
@Repository
public class UserDao extends BaseDao<User> {
    private final static String MATCH_COUNT_SQL = "SELECT count(*) FROM t_user WHERE user_name=? AND password=?";
    private final static String GET_USER_SQL = "SELECT * FROM t_user WHERE user_name=? AND password=?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";
    private final static String INSERT_USER_SQL = "INSERT INTO t_user(user_name, password, credits, last_ip, last_visit) VALUE(?,?,?,?,?)";
    private final static String ID_SQL = "SELECT * FROM t_user WHERE user_id=?";

    public UserDao() {
        super(User.class);
    }

    public int getMatchCount(String userName, String password) {
        return getJdbcTemplate().queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, int.class);
    }

    public User findByUserName(final String userName, String password) {
        final User user = new User();
        getJdbcTemplate().query(GET_USER_SQL, new Object[]{userName, password}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet);
                user.setUserID(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    @Cacheable(cacheNames = "users")
    public User getUserById(final int userId) {
        final User user = new User();
        System.out.println("query from database");
        getJdbcTemplate().query(ID_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserID(userId);
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void insertUser(User user) {
        getJdbcTemplate().update(INSERT_USER_SQL, user.getUserName(), user.getPassword()
                , user.getCredits(), user.getLastIP(), user.getLastVisit());
    }

    public void updateUserInfo(User user) {
        getJdbcTemplate().update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(), user.getLastIP(), user.getCredits(), user.getUserID());
    }
}
