package cn.hein.chain.handler.build;

import cn.hein.metadata.TableInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 额外信息设置处理器
 *
 * @author hein
 */
public class ExtraInfoHandler implements BuildTableInfoFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        data.forEach(tableInfo -> {
            AtomicBoolean hasDate = new AtomicBoolean(false);
            AtomicBoolean hasDateTime = new AtomicBoolean(false);
            AtomicBoolean hasDecimal = new AtomicBoolean(false);
            tableInfo.getFieldInfoList().forEach(fieldInfo -> {
                String sqlType = fieldInfo.getSqlType();
                if (sqlType.equals("date") || sqlType.equals("datetime")) {
                    hasDate.set(true);
                }
                if (sqlType.equals("time") || sqlType.equals("timestamp")) {
                    hasDateTime.set(true);
                }
                if (sqlType.equals("decimal")) {
                    hasDecimal.set(true);
                }
            });
            tableInfo.setHasDate(hasDate.get());
            tableInfo.setHasDateTime(hasDateTime.get());
            tableInfo.setHasDecimal(hasDecimal.get());
        });
    }
}
