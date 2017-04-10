package com.gayson.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by jixunzhen on 2017/4/8.
 */
public class MySQLFieldIncrementer extends AbstractColumnMaxValueIncrementer {
    private static final String VALUE_SQL = "select last_insert_id()";
    private static HashMap<String, Long> maxIdMap = new HashMap<String, Long>();
    private static HashMap<String, Long> nextIdMap = new HashMap<String, Long>();
    private long nextId = 0L;
    private long maxId = 0L;

    public synchronized long getNextKey(Class<?> clazz) {
        String name = getColName(clazz.getCanonicalName());
        if (name.equals(this.getColumnName())) {
            return getNextKey();
        } else {
            maxIdMap.put(getColumnName(), this.maxId);
            nextIdMap.put(getColumnName(), this.nextId);
            if (!maxIdMap.containsKey(name)) {
                this.maxId = 0;
                this.nextId = 0;
            } else {
                this.maxId = maxIdMap.get(name);
                this.nextId = nextIdMap.get(name);
            }
            this.setColumnName(name);
            return getNextKey();
        }
    }

    public boolean insertNewTable(Class<?> clazz) {
        String newName = getColName(clazz.getCanonicalName());

        Connection conn = DataSourceUtils.getConnection(this.getDataSource());
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isExist = false;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT COLUMN_NAME FROM information_schema.columns WHERE TABLE_NAME = \"" + this.getIncrementerName() + "\"");
            while (resultSet.next()) {
                String existName = resultSet.getString(1);
                if (existName.equals(newName)) {
                    return false;
                }
            }
            statement.execute("ALTER TABLE " + getIncrementerName() + " ADD " + newName + " INT DEFAULT 0");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            DataSourceUtils.releaseConnection(conn, this.getDataSource());
        }
        return true;
    }

    private String getColName(String qualifiedName) {
        String res;
        String[] strs = qualifiedName.split("\\.");
        res = strs[strs.length - 1];
        res = res.toLowerCase() + "_id";
        return res;
    }


    /**
     * this part is copy from the MySQLMaxValueIncrementer.class
     * to access the nextId and maxId
     */
    protected synchronized long getNextKey() throws DataAccessException {
        if (this.maxId == this.nextId) {
            Connection con = DataSourceUtils.getConnection(this.getDataSource());
            Statement stmt = null;

            try {
                stmt = con.createStatement();
                DataSourceUtils.applyTransactionTimeout(stmt, this.getDataSource());
                String ex = this.getColumnName();
                stmt.executeUpdate("update " + this.getIncrementerName() + " set " + ex + " = last_insert_id(" + ex + " + " + this.getCacheSize() + ")");
                ResultSet rs = stmt.executeQuery("select last_insert_id()");

                try {
                    if (!rs.next()) {
                        throw new DataAccessResourceFailureException("last_insert_id() failed after executing an update");
                    }

                    this.maxId = rs.getLong(1);
                } finally {
                    JdbcUtils.closeResultSet(rs);
                }

                this.nextId = this.maxId - (long) this.getCacheSize() + 1L;
            } catch (SQLException var14) {
                throw new DataAccessResourceFailureException("Could not obtain last_insert_id()", var14);
            } finally {
                JdbcUtils.closeStatement(stmt);
                DataSourceUtils.releaseConnection(con, this.getDataSource());
            }
        } else {
            ++this.nextId;
        }

        return this.nextId;
    }
}
