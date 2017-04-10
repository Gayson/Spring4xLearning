package com.gayson.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by jixunzhen on 2017/4/8.
 */
@Service
public class ScoreService {
    private static String ADD_SCORE_SQL = "UPDATE t_user u SET u.score=u.score + ? WHERE u.user_name=?";
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(ScoreService.class);

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addScore(String userName, int score) {
        jdbcTemplate.update(ADD_SCORE_SQL, score, userName);

        BasicDataSource dataSource = (BasicDataSource) jdbcTemplate.getDataSource();
        logger.info("connect count: " + dataSource.getNumActive());
    }
}
