package cn.hein.chain.handler.build;

import cn.hein.entity.TableInfo;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

import java.util.List;

import static cn.hein.common.Constant.IGNORE_TABLE_PREFIX;

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
        String[] strs = tableInfo.getTableName().split("_");
        StrBuilder entityName = StrBuilder.create();
        if (IGNORE_TABLE_PREFIX) {
            for (int i = 1; i < strs.length; i++) {
                entityName.append(StrUtil.upperFirst(strs[i]));
            }
        } else {
            for (String str : strs) {
                entityName.append(StrUtil.upperFirst(str));
            }
        }
        tableInfo.setEntityName(entityName.toString());
    }
}
