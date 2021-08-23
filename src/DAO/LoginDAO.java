package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {


    public boolean validateLogin(String username, String password) {
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();

        String verifyLogin = "SELECT * FROM LoginCredentials WHERE EmployeeId = '"
                + username + "' AND EmployeePass = sha2('" + password + "', 512)";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }
}
