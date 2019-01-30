package com.revolut.digitalbank.infrastructure;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String get(String key) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertiesPath = rootPath + "application.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesPath));
        return properties.getProperty(key);
    }

}
