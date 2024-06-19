package cn.hein.chain.handler.generate;

import cn.hein.chain.AbstractChainHandler;

/**
 * Code 生成责任链过滤器
 *
 * @author hein
 */
public interface CodeGenerateFilter<T> extends AbstractChainHandler<T> {

    @Override
    default String mark() {
        return "Code-Generate";
    }
}
