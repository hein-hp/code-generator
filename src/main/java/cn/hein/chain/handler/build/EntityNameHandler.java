package cn.hein.chain.handler.build;

import cn.hein.metadata.TableInfo;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

import java.util.List;

import static cn.hein.common.Constant.IGNORE_TABLE_PREFIX;
import static cn.hein.common.Constant.IGNORE_TABLE_PREFIX_VALUE;

/**
 * EntityName 设置处理器
 *
 * @author hein
 */
public class EntityNameHandler implements BuildTableInfoFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        data.forEach(this::handleTableName);
    }

    /**
     * 处理表名称前缀
     */
    private void handleTableName(TableInfo tableInfo) {
        String tableName = tableInfo.getTableName();
        if (IGNORE_TABLE_PREFIX) {
            tableName = tableName.replace(IGNORE_TABLE_PREFIX_VALUE, "");
        }
        List<String> strs = StrUtil.split(tableName, "_");
        StrBuilder entityName = StrBuilder.create();
        strs.forEach(str -> entityName.append(StrUtil.upperFirst(str)));
        tableInfo.setEntityName(entityName.toString());
    }
}
