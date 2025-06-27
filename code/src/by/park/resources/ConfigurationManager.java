package by.park.resources;

import java.util.ResourceBundle;

/*
 * Class for reading configuration (login / password for database, connection string, etc.)
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("by.park.resources.config");

    private ConfigurationManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

