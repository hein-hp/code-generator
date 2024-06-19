package cn.hein.chain.handler.build;

import cn.hein.chain.AbstractChainHandler;

/**
 * TableInfo 构建责任链过滤器
 *
 * @author hein
 */
public interface BuildTableInfoFilter<T> extends AbstractChainHandler<T> {

    @Override
    default String mark() {
        return "TableInfo-Build";
    }
}
