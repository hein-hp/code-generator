package cn.hein;

import cn.hein.chain.AbstractChainContext;
import cn.hein.metadata.TableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器启动类
 *
 * @author hein
 */
public class GeneratorApplication {

    public static void doGenerate() {
        AbstractChainContext<List<TableInfo>> context = new AbstractChainContext<>();
        context.handler(new ArrayList<>());
    }
}