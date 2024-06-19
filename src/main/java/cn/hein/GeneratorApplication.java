package cn.hein;

import cn.hein.entity.TableInfo;
import cn.hein.chain.BuildTableInfoChainContext;

import java.util.ArrayList;
import java.util.List;

public class GeneratorApplication {
    public static void main(String[] args) {
        BuildTableInfoChainContext<List<TableInfo>> context = new BuildTableInfoChainContext<>();
        ArrayList<TableInfo> data = new ArrayList<>();
        context.handler(data);
        System.out.println("data = " + data);
    }
}