package com.revolut.digitalbank.infrastructure;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static void main(String[] args) throws IOException {
        System.out.println(PropertiesUtil.get("jetty.jersey.packages"));
        System.out.println(PropertiesUtil.get("jetty.application.package"));
        System.out.println(Integer.valueOf(PropertiesUtil.get("jetty.port")));
        System.out.println(PropertiesUtil.get("jetty.context.path"));
        System.out.println(PropertiesUtil.get("jetty.path.spec"));
        System.out.println(Integer.valueOf(PropertiesUtil.get("jetty.init.order")));
    }

    public static String get(String key) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertiesPath = rootPath + "application.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesPath));
        return properties.getProperty(key);
    }

}
