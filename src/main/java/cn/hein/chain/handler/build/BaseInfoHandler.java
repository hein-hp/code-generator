package cn.hein.chain.handler.build;

import cn.hein.entity.FieldInfo;
import cn.hein.entity.TableInfo;
import cn.hein.factory.ConnectionFactory;
import cn.hein.toolkit.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hein.common.Constant.GENERATE_ALL_TABLE;

/**
 * TableInfo 基础信息设置处理器
 *
 * @author hein
 */
@Slf4j
public class BaseInfoHandler implements BuildTableInfoFilter<List<TableInfo>> {

    private static final String SHOW_TABLE_STATUS = "SHOW TABLE STATUS;";
    private static final String SHOW_FULL_FIELDS_FROM_TABLE = "SHOW FULL FIELDS FROM %s;";

    @Override
    public void handle(List<TableInfo> data) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            handleTable(data, connection);
            handleField(data, connection);
        } catch (SQLException e) {
            log.error("Failed to access table", e);
        }
    }

    private void handleField(List<TableInfo> data, Connection connection) throws SQLException {
        Map<String, TableInfo> map = data.stream().collect(Collectors.toMap(TableInfo::getTableName, tableInfo -> tableInfo));
        map.forEach((tableName, tableInfo) -> {
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(String.format(SHOW_FULL_FIELDS_FROM_TABLE, tableName));
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                List<FieldInfo> fieldInfoList = new ArrayList<>();
                while (resultSet.next()) {
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setFieldName(resultSet.getString("Field"));
                    fieldInfo.setSqlType(removeSize(resultSet.getString("Type")));
                    fieldInfo.setComment(resultSet.getString("Comment"));
                    fieldInfoList.add(fieldInfo);
                }
                map.get(tableName).setFieldInfoList(fieldInfoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String removeSize(String type) {
        int index = type.indexOf('(');
        if (index != -1) {
            return type.substring(0, index);
        }
        return type;
    }

    private void handleTable(List<TableInfo> data, Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TABLE_STATUS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (Boolean.TRUE.equals(GENERATE_ALL_TABLE)) {
                while (resultSet.next()) {
                    TableInfo tableInfo = new TableInfo();
                    String tableName = resultSet.getString("Name");
                    tableInfo.setTableName(tableName);
                    tableInfo.setComment(resultSet.getString("Comment"));
                    data.add(tableInfo);
                }
            } else {
                Set<String> tableNames = Arrays.stream(PropertiesUtils.getProperties("generate.table.names")).collect(Collectors.toSet());
                while (resultSet.next()) {
                    String tableName = resultSet.getString("Name");
                    if (tableNames.contains(tableName)) {
                        TableInfo tableInfo = new TableInfo();
                        tableInfo.setTableName(tableName);
                        tableInfo.setComment(resultSet.getString("Comment"));
                        data.add(tableInfo);
                    }
                }
            }
        }
    }
}
