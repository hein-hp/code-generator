package cn.hein.entity;

import lombok.Data;

/**
 * 字段信息
 *
 * @author hein
 */
@Data
public class FieldInfo {

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 实体类属性名称
     */
    private String propertyName;

    /**
     * 字段类型
     */
    private String sqlType;

    /**
     * 实体类属性类型
     */
    private String javaType;

    /**
     * 字段注释
     */
    private String comment;
}
