package cn.hein.chain;

import cn.hein.chain.handler.BaseInfoHandler;
import cn.hein.chain.handler.EntityFieldAndTypeHandler;
import cn.hein.chain.handler.EntityNameHandler;

import java.util.List;

/**
 * 构建 TableInfo 责任链容器
 *
 * @author hein
 */
public class BuildTableInfoChainContext<T> {

    private List<BuildTableInfoChainHandler<T>> buildTableInfoChainHandlerContainer;

    @SuppressWarnings("unchecked")
    private void load() {
        buildTableInfoChainHandlerContainer = List.of(
                (BuildTableInfoChainHandler<T>) new BaseInfoHandler(),
                (BuildTableInfoChainHandler<T>) new EntityNameHandler(),
                (BuildTableInfoChainHandler<T>) new EntityFieldAndTypeHandler()
        );
    }

    /**
     * 责任链组件执行
     */
    public void handler(T data) {
        if (buildTableInfoChainHandlerContainer == null || buildTableInfoChainHandlerContainer.isEmpty()) {
            load();
        }
        buildTableInfoChainHandlerContainer.forEach(each -> each.handle(data));
    }
}
