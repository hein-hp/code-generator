package cn.hein.toolkit;

/**
 * 文件工具类
 *
 * @author hein
 */
public class FileUtils {

    public static String getRootPath() {
        return System.getProperty("user.dir");
    }

    public static String getPackagePath(String packageName) {
        return getRootPath() + "\\src\\main\\java\\" + packageName.replace(".", "\\") + "\\";
    }
}
