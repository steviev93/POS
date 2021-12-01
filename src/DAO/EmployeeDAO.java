package DAO;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public boolean Create(Employee employee) throws SQLException {

        String createStatement = "INSERT INTO Employees(EmployeeName,PhoneNumber,Email,EmployedSince,HourlySalary,TitleId)" +
                " VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(createStatement);
            prepStmt.setString(1, employee.getEmployeeName());
            prepStmt.setString(2, employee.getPhoneNumber());
            prepStmt.setString(3, employee.getEmail());
            prepStmt.setFloat(4, employee.getHourlySalary());
            prepStmt.setInt(5, employee.getTitleId());

            prepStmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    public ObservableList<Employee> Read() throws SQLException {

        String createStatement = "SELECT * FROM Employees";
        ObservableList<Employee> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Employee(
                        queryResult.getInt("EmployeeId"),
                        queryResult.getString("EmployeeName"),
                        queryResult.getString("PhoneNumber"),
                        queryResult.getString("Email"),
                        queryResult.getDate("EmployedSince"),
                        queryResult.getFloat("HourlySalary"),
                        queryResult.getInt("TitleId")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }
    public Integer ReadLast() throws SQLException {

        String getLastStatement = "SELECT EmployeeId FROM Employees WHERE EmployeeId=(SELECT MAX(EmployeeId) FROM Employees)";
        try {
            Statement lastLoginStatement = connection.createStatement();
            ResultSet lastLoginResult = lastLoginStatement.executeQuery(getLastStatement);
            lastLoginResult.next();

            int orderNo = lastLoginResult.getInt("EmployeeId");
            return orderNo;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return -1;
        }
    }
    public boolean Update(Employee employee) {
        String updateStatement = "UPDATE Employees SET PhoneNumber = ?, Email = ?," +
                " HourlySalary = ?, TitleId = ? WHERE EmployeeId = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(updateStatement);
            prepStmt.setString(1, employee.getPhoneNumber());
            prepStmt.setString(2, employee.getEmail());
            prepStmt.setFloat(3, employee.getHourlySalary());
            prepStmt.setInt(4, employee.getTitleId());
            prepStmt.setInt(5, employee.getEmployeeId());

            prepStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }
    public boolean Delete(Employee employee) throws SQLException {
        String deleteStatement = "DELETE FROM Employees WHERE EmployeeId = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(deleteStatement);
            prepStmt.execute();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(deleteStatement);
            if (result == 1) return true;
            else return false;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }
}
