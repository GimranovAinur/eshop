package andersen.lab.eshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private final static String CONNECTION_URI = "jdbc:postgresql://localhost:5432/";

    private final static String DB_NAME = "bookshelf";

    private final static String LOGIN = "postgres";

    private final static String PASSWORD = "psqlpass";

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(CONNECTION_URI + DB_NAME, LOGIN, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Не удалось подключиться к базе данных: " + e.getMessage());
            }
        }
        return conn;
    }

}
