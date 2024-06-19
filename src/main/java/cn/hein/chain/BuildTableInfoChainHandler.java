package cn.hein.chain;

/**
 * 构建 TableInfo 处理器接口
 *
 * @author hein
 */
public interface BuildTableInfoChainHandler<T> {

    void handle(T data);
}
