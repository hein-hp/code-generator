package cn.hein.chain.handler.build;

import cn.hein.metadata.FieldInfo;
import cn.hein.metadata.TableInfo;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

import java.util.List;

import static cn.hein.common.Constant.SQL_TO_JAVA_TYPE;

/**
 * EntityField & EntityType 设置处理器
 *
 * @author hein
 */
public class EntityFieldAndTypeHandler implements BuildTableInfoFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        data.forEach(tableInfo -> tableInfo.getFieldInfoList().forEach(this::handleFieldName));
    }

    private void handleFieldName(FieldInfo fieldInfo) {
        String[] strs = fieldInfo.getFieldName().split("_");
        StrBuilder propertyName = StrBuilder.create(strs[0]);
        if (strs.length > 1) {
            for (int i = 1; i < strs.length; i++) {
                propertyName.append(StrUtil.upperFirst(strs[i]));
            }
        }
        fieldInfo.setPropertyName(propertyName.toString());
        fieldInfo.setJavaType(SQL_TO_JAVA_TYPE.get(fieldInfo.getSqlType()));
    }
}
