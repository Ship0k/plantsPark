package by.park.connect;

import static by.park.resources.ConfigurationManager.getProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Class for establishing communication with the database
 */
public class SQLServer {
    private static final String DRIVER = getProperty("DRIVER");
    private static final String URL = getProperty("URL");
    private static final String USERNAME = getProperty("USERNAME");
    private static final String PASSWORD = getProperty("PASSWORD");

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
