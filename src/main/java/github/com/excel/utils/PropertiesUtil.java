package github.com.excel.utils;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/1 12:26
 * Email: renhongqiang1397@gmail.com
 */
public class PropertiesUtil {
    private static final String EXCEL_CONFIG = "excel_map.properties";
    private static final String SEPARATOR = ",";
    private static Properties properties = new Properties();

    public PropertiesUtil() {
    }

    public static Properties getPropertiesFile(String propFile) {
        InputStream in = ExportUtils.class.getClassLoader().getResourceAsStream(propFile);
        try {
            if(in == null) {
                return properties;
            }

            properties.load(in);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return properties;
    }

    public static Object getPropertyValue(String proName) {
        properties = getPropertiesFile(EXCEL_CONFIG);
        return StringUtils.isNotBlank(proName) && null != properties?properties.get(proName):null;
    }

    public static List<Integer> getPropertyAsList(String proName) {
        Object obj = getPropertyValue(proName);
        if(null == obj) {
            return null;
        } else {
            String[] values = obj.toString().split(SEPARATOR);
            ArrayList objs = new ArrayList();
            String[] var4 = values;
            int var5 = values.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                objs.add(new Integer(s));
            }

            return objs;
        }
    }

    public static List<Integer> getInPropertyAsList(String proName) {
        Properties properties = getPropertiesFile("excel_map.properties");
        if(null == properties) {
            return null;
        } else {
            Object obj = properties.get(proName);
            String[] values = obj.toString().split(",");
            ArrayList objs = new ArrayList();
            String[] var5 = values;
            int var6 = values.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String s = var5[var7];
                objs.add(new Integer(s));
            }

            return objs;
        }
    }
}
