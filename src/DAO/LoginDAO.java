package DAO;

import Models.Credentials;

import java.sql.*;

public class LoginDAO {

    private Connection connection;

    public LoginDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public boolean Create(String id, String password, int isAdmin) {

        String createStatement = "INSERT INTO LoginCredentials(EmployeeId,EmployeePass,isAdmin) " +
                "VALUES (?, sha2(?, 512), " +
                "?)";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(createStatement);
            prepStmt.setInt(1, Integer.parseInt(id));
            prepStmt.setString(2, password);
            prepStmt.setBoolean(3, isAdmin == 1? true:false);
            prepStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    public Credentials validateLogin(String username, String password) {

        String verifyLogin = "SELECT * FROM LoginCredentials " +
                "WHERE EmployeeId = ? AND EmployeePass = sha2(?, 512)";


        try {
            PreparedStatement prepStmt = connection.prepareStatement(verifyLogin);

            prepStmt.setInt(1, Integer.parseInt(username));
            prepStmt.setString(2, password);
            ResultSet queryResult = prepStmt.executeQuery();

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
        String lastLogin = "UPDATE LoginCredentials SET IsAdmin = ? WHERE EmployeeId = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(lastLogin);
            prepStmt.setBoolean(1, isAdmin == 1? true:false);
            prepStmt.setInt(2, employeeId);
            prepStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
    }
    public boolean getIsAdmin(int employeeId) throws SQLException {
        String sql = "SELECT IsAdmin FROM LoginCredentials WHERE EmployeeId = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setInt(0, employeeId);
        ResultSet rs = prepStmt.executeQuery();
        return rs.getBoolean("IsAdmin");
    }
}
