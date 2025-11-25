package pl.footballapp.bartek.repository;

import java.sql.*;

public class DatabaseConnection {

    private Connection connection;

    private static DatabaseConnection databaseConnection;

    public static DatabaseConnection getInstance() {

        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Statement createStatement() {
        if (connection != null) {
            try {
                return connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("No connection with DB");
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        if (connection != null) {
            try {
                return connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("No connection with DB");
        }
    }

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/sql/season.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
