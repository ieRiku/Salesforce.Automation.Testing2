package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
