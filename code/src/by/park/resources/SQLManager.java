package by.park.resources;

import java.util.ResourceBundle;

/*
 * Class for reading SQL queries from file
 */
public class SQLManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("by.park.resources.requests");

    private SQLManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
