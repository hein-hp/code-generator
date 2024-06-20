package cn.hein.common;

import cn.hein.toolkit.PropertiesUtils;

import java.util.Map;

/**
 * 常量类
 *
 * @author hein
 */
public class Constant {

    public static Boolean IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));
    public static String IGNORE_TABLE_PREFIX_VALUE = PropertiesUtils.getProperty("ignore.table.prefix.value");
    public static Boolean GENERATE_ALL_TABLE = Boolean.valueOf(PropertiesUtils.getProperty("generate.all.table"));
    public static String ENTITY_PACKAGE = PropertiesUtils.getProperty("generate.entity.package");
    public static String MAPPER_PACKAGE = PropertiesUtils.getProperty("generate.mapper.package");
    public static String SERVICE_PACKAGE = PropertiesUtils.getProperty("generate.service.package");
    public static String CONTROLLER_PACKAGE = PropertiesUtils.getProperty("generate.controller.package");
    public static String AUTHOR = PropertiesUtils.getProperty("author");

    public static Map<String, String> SQL_TO_JAVA_TYPE = Map.ofEntries(
            Map.entry("varchar", "String"),
            Map.entry("char", "String"),
            Map.entry("text", "String"),
            Map.entry("longtext", "String"),
            Map.entry("mediumtext", "String"),
            Map.entry("tinytext", "String"),
            Map.entry("datetime", "Date"),
            Map.entry("date", "Date"),
            Map.entry("time", "Date"),
            Map.entry("timestamp", "Date"),
            Map.entry("int", "Integer"),
            Map.entry("tinyint", "Integer"),
            Map.entry("smallint", "Integer"),
            Map.entry("mediumint", "Integer"),
            Map.entry("bigint", "Long"),
            Map.entry("decimal", "BigDecimal"),
            Map.entry("float", "Float"),
            Map.entry("double", "Double"),
            Map.entry("bit", "Boolean"),
            Map.entry("tinyblob", "byte[]"),
            Map.entry("blob", "byte[]"),
            Map.entry("mediumblob", "byte[]"),
            Map.entry("longblob", "byte[]")
    );
}
