package cn.hein.chain;

import cn.hein.chain.handler.build.BaseInfoHandler;
import cn.hein.chain.handler.build.EntityFieldAndTypeHandler;
import cn.hein.chain.handler.build.EntityNameHandler;

import java.util.List;

/**
 * 构建 TableInfo 责任链容器
 *
 * @author hein
 */
public class AbstractChainContext<T> {

    private List<AbstractChainHandler<T>> abstractChainHandlerContainer;

    @SuppressWarnings("unchecked")
    private void load() {
        abstractChainHandlerContainer = List.of(
                (AbstractChainHandler<T>) new BaseInfoHandler(),
                (AbstractChainHandler<T>) new EntityNameHandler(),
                (AbstractChainHandler<T>) new EntityFieldAndTypeHandler()
        );
    }

    /**
     * 责任链组件执行
     */
    public void handler(T data) {
        if (abstractChainHandlerContainer == null || abstractChainHandlerContainer.isEmpty()) {
            load();
        }
        abstractChainHandlerContainer.forEach(each -> each.handle(data));
    }
}
