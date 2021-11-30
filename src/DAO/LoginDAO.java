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
    public boolean Create(String id, String password, int isAdmin) throws SQLException {

        String createStatement = "INSERT INTO LoginCredentials(EmployeeId,EmployeePass,isAdmin) VALUES (" +
                id + ", sha2('" +
                password + "', 512), " +
                isAdmin + ")";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createStatement);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    public Credentials validateLogin(String username, String password) {

        String verifyLogin = "SELECT * FROM LoginCredentials WHERE EmployeeId = '"
                + username + "' AND EmployeePass = sha2('" + password + "', 512)";

        try {
            Statement verifyStatement = connection.createStatement();
            ResultSet queryResult = verifyStatement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                java.util.Date dt = new java.util.Date();

                java.text.SimpleDateFormat sdf =
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String currentTime = sdf.format(dt);

                String insertLog = "INSERT INTO loginLogging(loginDate,employeeId) VALUES ('" +
                        currentTime + "', '" +
                        username + "')";
                Statement insertStatement = connection.createStatement();
                int result = insertStatement.executeUpdate(insertLog);
                if (result == 1) return new Credentials(
                        queryResult.getString("EmployeeId"),
                        queryResult.getBoolean("isAdmin"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return new Credentials("",false);
    }
    public boolean UpdateIsAdmin(int employeeId, int isAdmin) {
        String lastLogin = "UPDATE LoginCredentials SET IsAdmin = " + isAdmin + " WHERE EmployeeId = " + employeeId;
        try {
            Statement lastLoginStatement = connection.createStatement();
            int lastLoginResult = lastLoginStatement.executeUpdate(lastLogin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
    }
}
