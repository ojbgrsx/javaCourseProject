package sample;

import java.sql.*;

public class ConnectionDB {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/javaproject";
            String username = "root";
            String password = "root";
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
