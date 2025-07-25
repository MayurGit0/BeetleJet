package test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
