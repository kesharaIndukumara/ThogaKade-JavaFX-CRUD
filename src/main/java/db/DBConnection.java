package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    private static DBConnection instance;
    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thoga_kade", "root", "1234");
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws SQLException{
        return instance == null ? instance = new DBConnection() : instance;
    }
}
