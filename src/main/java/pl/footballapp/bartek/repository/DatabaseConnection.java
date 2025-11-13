package pl.footballapp.bartek.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection;

    private static DatabaseConnection databaseConnection;

    public static DatabaseConnection getInstance(){

        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/sql/season.db");
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
