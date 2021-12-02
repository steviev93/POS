package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection dbLink;
    private String dbName = "SteviesFoodShack";
    private String dbUser = "root";
    private String dbPassword = "";

    protected Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/"+dbName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbUser,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return dbLink;
    }
}
