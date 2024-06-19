package cn.hein.chain.handler.generate;

import cn.hein.metadata.TableInfo;
import cn.hein.toolkit.FileUtils;
import cn.hutool.core.io.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static cn.hein.common.Constant.*;

/**
 * Controller Code 生成处理器
 *
 * @author hein
 */
public class ControllerGenerateHandler implements CodeGenerateFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        String packagePath = FileUtils.getPackagePath(CONTROLLER_PACKAGE);
        data.forEach(tableInfo -> {
            String entityName = tableInfo.getEntityName();
            FileUtil.touch(packagePath + entityName + "Controller.java");
            try {
                BufferedWriter writer = FileUtil.getWriter(packagePath + entityName + "Controller.java", "UTF-8", false);
                writer.write("package " + CONTROLLER_PACKAGE + ";");
                writer.newLine();
                writer.newLine();
                writer.write("import org.springframework.web.bind.annotation.RestController;");
                writer.newLine();
                writer.newLine();
                writer.write("/**");
                writer.newLine();
                writer.write(" * " + tableInfo.getComment() + "控制层");
                writer.newLine();
                writer.write(" * ");
                writer.newLine();
                writer.write(" * @author " + AUTHOR);
                writer.newLine();
                writer.write(" */");
                writer.newLine();
                writer.write("@RestController");
                writer.newLine();
                writer.write("public class " + entityName + "Controller {");
                writer.newLine();
                writer.write("}");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
