package cn.hein.metadata;

import lombok.Data;

import java.util.List;

/**
 * 表信息
 *
 * @author hein
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 字段信息
     */
    private List<FieldInfo> fieldInfoList;
}
