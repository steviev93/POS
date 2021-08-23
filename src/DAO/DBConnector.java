package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private Connection dbLink;
    private String dbName = "SteviesFoodShack";
    private String dbUser = "root";
    private String dbPassword = "ii314B,epc00";

    protected Connection getConnection() {

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
