package cn.hein.factory;

import cn.hein.toolkit.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQL 连接工厂
 */
@Slf4j
public class ConnectionFactory {

    final static String driverName = PropertiesUtils.getProperty("jdbc.driver");
    final static String username = PropertiesUtils.getProperty("jdbc.username");
    final static String password = PropertiesUtils.getProperty("jdbc.password");
    final static String url = PropertiesUtils.getProperty("jdbc.url");

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError();
        }
    }

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("Failed to obtain the database connection.", e);
            throw e;
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Failed to close the database connection", e);
            }
        }
    }
}
