# 一个小而美的代码生成器

code-generator

## 介绍

一个小而美的代码生成器，操作简单，不依赖任何插件，自动生成 entity、mapper、service 等大量重复代码。

## 使用说明

整体只有三步，生成之后即可删除 maven 依赖和相关配置文件。

### 导入 maven 依赖

```xml
<dependency>
    <groupId>cn.hein</groupId>
    <artifactId>code-generator</artifactId>
    <version>1.0</version>
</dependency>
```

### 编写配置文件

名称 code-gernerate-config.properties

示例如下：

```java
# code-generator config

########## database config ##########
jdbc.url=jdbc:mysql://mysql-ip:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
jdbc.username=username
jdbc.password=password
jdbc.driver=com.mysql.cj.jdbc.Driver

######### generator config ##########
# 是否忽略表前缀
ignore.table.prefix=true
# 忽略表前缀
ignore.table.prefix.value=t_
# 是否为所有表生成代码（generate.all.table 优先级更高）
generate.all.table=false
# 需要生成代码的表名称
generate.table.names=t_product_info
# 生成实体类的包路径
generate.entity.package=cn.hein.dao.entity
# 生成Mapper的包路径
generate.mapper.package=cn.hein.dao.mapper
# 生成Service的包路径
generate.service.package=cn.hein.service
# 生成Controller的包路径
generate.controller.package=cn.hein.controller

############ extra info ##############
author=hein
```

### 运行 GenerateApplication 类

在任意一个类中，编写 main 方法：

```java
public static void main(String[] args) {
    GeneratorApplication.doGenerate();
}
```

### 生成结果

以下面的 table 为例：

```sql
CREATE TABLE `t_product_info` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` VARCHAR ( 30 ) CHARACTER 
  SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司ID',
  `code` VARCHAR ( 11 ) CHARACTER 
  SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编码',
  `product_name` VARCHAR ( 200 ) CHARACTER 
  SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `price` DECIMAL ( 15, 2 ) DEFAULT NULL COMMENT '价格',
  `sku_type` TINYINT DEFAULT NULL COMMENT 'sku类型',
  `color_type` TINYINT DEFAULT NULL COMMENT '颜色类型',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_date` DATE DEFAULT NULL COMMENT '创建日期',
  `stock` BIGINT DEFAULT NULL COMMENT '库存',
  `status` TINYINT DEFAULT NULL COMMENT '状态',
  PRIMARY KEY ( `id` ),
  UNIQUE KEY `code` ( `code` ),
KEY `sku_type` ( `sku_type` ) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '产品详情表';
```

最后会生成如下文件：

```
cn
\---hein
    |   CodeGenerateTestApplication.java
    +---controller
    |       ProductInfoController.java
    +---dao
    |   +---entity
    |   |       ProductInfo.java
    |   \---mapper
    |           ProductInfoMapper.java
    \---service
        |   ProductInfoService.java
        \---impl
                ProductInfoServiceImpl.java
```

各个类结果如下：

```java
package cn.hein.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 产品详情表控制层
 * 
 * @author hein
 */
@RestController
public class ProductInfoController {
}
```

```java
package cn.hein.dao.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品详情表实体
 * 
 * @author hein
 */
@Data
public class ProductInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * sku类型
     */
    private Integer skuType;

    /**
     * 颜色类型
     */
    private Integer colorType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 状态
     */
    private Integer status;
}
```

```java
package cn.hein.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品详情表持久层
 * 
 * @author hein
 */
@Mapper
public interface ProductInfoMapper {
}
```

```java
package cn.hein.service.impl;

import cn.hein.service.ProductInfoService;
import org.springframework.stereotype.Service;

/**
 * 产品详情表接口实现层
 * 
 * @author hein
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
}
```

```java
package cn.hein.service;

/**
 * 产品详情表接口层
 * 
 * @author hein
 */
public interface ProductInfoService {
}
```
