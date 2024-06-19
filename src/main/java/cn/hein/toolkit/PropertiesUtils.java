package cn.hein.toolkit;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置工具类
 */
public class PropertiesUtils {

    private static final String PROPERTIES_FILE_NAME = "config.properties";
    private static final String SPLIT_SIGN = ",";

    private static final Map<String, Object> propertiesMap = new ConcurrentHashMap<>();

    private PropertiesUtils() {}

    private static void load() {
        Props props = PropsUtil.get(PROPERTIES_FILE_NAME);
        props.forEach((key, value) -> propertiesMap.put((String) key, value));
    }

    public static String getProperty(String keyName) {
        if (propertiesMap.isEmpty()) {
            load();
        }
        if (!propertiesMap.containsKey(keyName)) {
            throw new RuntimeException("property not exits");
        }
        return (String) propertiesMap.get(keyName);
    }

    public static String[] getProperties(String keyName) {
        if (propertiesMap.isEmpty()) {
            load();
        }
        if (!propertiesMap.containsKey(keyName)) {
            throw new RuntimeException("properties not exits");
        }
        return ((String) propertiesMap.get(keyName)).split(SPLIT_SIGN);
    }
}
