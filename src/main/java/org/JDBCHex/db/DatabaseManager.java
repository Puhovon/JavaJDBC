package org.JDBCHex.db;

import java.sql.*;

public class DatabaseManager {
    static final String DB_URL = "jdbc:postgresql://postgres:5432/library_db";
    static final String USER = "postgres";
    static final String PASS = "123";
    public void Connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from books");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
