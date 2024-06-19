package cn.hein.chain;

/**
 * 抽象责任链处理器接口
 *
 * @author hein
 */
public interface AbstractChainHandler<T> {

    /**
     * 处理
     */
    void handle(T data);

    /**
     * 责任链组件标识
     */
    String mark();
}
