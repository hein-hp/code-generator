package cn.hein.chain.handler.generate;

import cn.hein.metadata.TableInfo;
import cn.hein.toolkit.FileUtils;
import cn.hutool.core.io.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static cn.hein.common.Constant.AUTHOR;
import static cn.hein.common.Constant.ENTITY_PACKAGE;

/**
 * Entity Code 生成处理器
 *
 * @author hein
 */
public class EntityGenerateHandler implements CodeGenerateFilter<List<TableInfo>> {

    @Override
    public void handle(List<TableInfo> data) {
        String packagePath = FileUtils.getPackagePath(ENTITY_PACKAGE);
        data.forEach(tableInfo -> {
            String entityName = tableInfo.getEntityName();
            FileUtil.touch(packagePath + entityName + ".java");
            try {
                BufferedWriter writer = FileUtil.getWriter(packagePath + entityName + ".java", "UTF-8", false);
                writer.write("package " + ENTITY_PACKAGE + ";");
                writer.newLine();
                writer.newLine();
                writer.write("import lombok.Data;");
                writer.newLine();
                writer.newLine();
                writer.write("import java.io.Serial;");
                writer.newLine();
                writer.write("import java.io.Serializable;");
                writer.newLine();
                if (tableInfo.getHasDecimal().equals(Boolean.TRUE)) {
                    writer.write("import java.math.BigDecimal;");
                    writer.newLine();
                }
                if (tableInfo.getHasDate().equals(Boolean.TRUE) || tableInfo.getHasDateTime().equals(Boolean.TRUE)) {
                    writer.write("import java.util.Date;");
                    writer.newLine();
                    writer.newLine();
                }
                writer.write("/**");
                writer.newLine();
                writer.write(" * " + tableInfo.getComment() + "实体");
                writer.newLine();
                writer.write(" * ");
                writer.newLine();
                writer.write(" * @author " + AUTHOR);
                writer.newLine();
                writer.write(" */");
                writer.newLine();
                writer.write("@Data");
                writer.newLine();
                writer.write("public class " + entityName + " implements Serializable {");
                writer.newLine();
                writer.newLine();
                writer.write("    @Serial");
                writer.newLine();
                writer.write("    private static final long serialVersionUID = 1L;");
                writer.newLine();
                tableInfo.getFieldInfoList().forEach(fieldInfo -> {
                    try {
                        writer.newLine();
                        writer.write("    /**");
                        writer.newLine();
                        writer.write("     * " + fieldInfo.getComment());
                        writer.newLine();
                        writer.write("     */");
                        writer.newLine();
                        writer.write("    private " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.write("}");
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
