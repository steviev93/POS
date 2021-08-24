package DAO;

import Models.Credentials;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    private Connection connection;

    public LoginDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }



    public Credentials validateLogin(String username, String password) {

        String verifyLogin = "SELECT * FROM LoginCredentials WHERE EmployeeId = '"
                + username + "' AND EmployeePass = sha2('" + password + "', 512)";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) return new Credentials(
                    queryResult.getString("EmployeeId"),
                    queryResult.getBoolean("isAdmin"));

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return new Credentials("",false);
    }
}
