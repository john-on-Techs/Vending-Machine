package com.creativityskills.jotech.db;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBHandler {
    @Resource(lookup = "java:/vmDS")
    private DataSource dataSource;
    @Produces
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }
}
