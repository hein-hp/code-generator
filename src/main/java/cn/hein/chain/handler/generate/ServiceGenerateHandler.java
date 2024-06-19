package cn.hein.chain.handler.generate;

import cn.hein.metadata.TableInfo;
import cn.hein.toolkit.FileUtils;
import cn.hutool.core.io.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static cn.hein.common.Constant.*;

/**
 * Service Code 生成处理器
 *
 * @author hein
 */
public class ServiceGenerateHandler implements CodeGenerateFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        writeService(data);
        writeServiceImpl(data);
    }

    private void writeServiceImpl(List<TableInfo> data) {
        String packagePath = FileUtils.getPackagePath(SERVICE_PACKAGE);
        data.forEach(tableInfo -> {
            String entityName = tableInfo.getEntityName();
            FileUtil.touch(packagePath + "impl\\" + entityName + "ServiceImpl.java");
            try {
                BufferedWriter writer = FileUtil.getWriter(packagePath + "impl\\" + entityName + "ServiceImpl.java", "UTF-8", false);
                writer.write("package " + SERVICE_PACKAGE + ".impl;");
                writer.newLine();
                writer.newLine();
                writer.write("import " + SERVICE_PACKAGE + "." + entityName + "Service;");
                writer.newLine();
                writer.write("import org.springframework.stereotype.Service;");
                writer.newLine();
                writer.newLine();
                writer.write("/**");
                writer.newLine();
                writer.write(" * " + tableInfo.getComment() + "接口实现层");
                writer.newLine();
                writer.write(" * ");
                writer.newLine();
                writer.write(" * @author " + AUTHOR);
                writer.newLine();
                writer.write(" */");
                writer.newLine();
                writer.write("@Service");
                writer.newLine();
                writer.write("public class " + entityName + "ServiceImpl implements " + entityName + "Service {");
                writer.newLine();
                writer.write("}");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void writeService(List<TableInfo> data) {
        String packagePath = FileUtils.getPackagePath(SERVICE_PACKAGE);
        data.forEach(tableInfo -> {
            String entityName = tableInfo.getEntityName();
            FileUtil.touch(packagePath + entityName + "Service.java");
            try {
                BufferedWriter writer = FileUtil.getWriter(packagePath + entityName + "Service.java", "UTF-8", false);
                writer.write("package " + SERVICE_PACKAGE + ";");
                writer.newLine();
                writer.newLine();
                writer.write("/**");
                writer.newLine();
                writer.write(" * " + tableInfo.getComment() + "接口层");
                writer.newLine();
                writer.write(" * ");
                writer.newLine();
                writer.write(" * @author " + AUTHOR);
                writer.newLine();
                writer.write(" */");
                writer.newLine();
                writer.write("public interface " + entityName + "Service {");
                writer.newLine();
                writer.write("}");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
