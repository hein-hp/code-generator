package cn.hein.chain.handler.generate;

import cn.hein.metadata.TableInfo;
import cn.hein.toolkit.FileUtils;
import cn.hutool.core.io.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static cn.hein.common.Constant.*;

/**
 * Mapper Code 生成处理器
 *
 * @author hein
 */
public class MapperGenerateHandler implements CodeGenerateFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        String packagePath = FileUtils.getPackagePath(MAPPER_PACKAGE);
        data.forEach(tableInfo -> {
            String entityName = tableInfo.getEntityName();
            FileUtil.touch(packagePath + entityName + "Mapper.java");
            try {
                BufferedWriter writer = FileUtil.getWriter(packagePath + entityName + "Mapper.java", "UTF-8", false);
                writer.write("package " + MAPPER_PACKAGE + ";");
                writer.newLine();
                writer.newLine();
                writer.write("import org.apache.ibatis.annotations.Mapper;");
                writer.newLine();
                writer.newLine();
                writer.write("/**");
                writer.newLine();
                writer.write(" * " + tableInfo.getComment() + "持久层");
                writer.newLine();
                writer.write(" * ");
                writer.newLine();
                writer.write(" * @author " + AUTHOR);
                writer.newLine();
                writer.write(" */");
                writer.newLine();
                writer.write("@Mapper");
                writer.newLine();
                writer.write("public interface " + entityName + "Mapper {");
                writer.newLine();
                writer.write("}");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
