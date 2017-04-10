package com.gayson.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by jixunzhen on 2017/4/10.
 */
@Repository
public abstract class BaseDao<T> {
    private HibernateTemplate hibernateTemplate;
    private JdbcTemplate jdbcTemplate;
    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T get(Serializable id) {
        return (T) hibernateTemplate.get(entityClass, id);
    }

    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
