package cn.hein.handler;

import cn.hein.factory.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 表处理器
 *
 * @author hein
 */
@Slf4j
public class TableHandler {

    private static final String SHOW_TABLE_STATUS = "SHOW TABLE STATUS";

    public static void accessTable() {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TABLE_STATUS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                String tableName = resultSet.getString("Name");
                String comment = resultSet.getString("Comment");
                log.info("tableName: {}, comment: {}", tableName, comment);
            }
        } catch (Exception e) {
            log.error("Failed to access table", e);
        }
    }
}
