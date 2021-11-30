package DAO;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public boolean Create(Employee employee) throws SQLException {

        String createStatement = "INSERT INTO Employees(EmployeeName,PhoneNumber,Email,EmployedSince,HourlySalary,TitleId) VALUES ('" +
                employee.getEmployeeName() + "', '" +
                employee.getPhoneNumber() + "', '" +
                employee.getEmail() + "', NOW(), " +
                employee.getHourlySalary() + ", " +
                employee.getTitleId() +
                ")";

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
        String updateStatement = "UPDATE Employees SET PhoneNumber = '" +
                employee.getPhoneNumber() + "', Email = '" +
                employee.getEmail() + "', HourlySalary = " +
                employee.getHourlySalary() + ", TitleId = " +
                employee.getTitleId() + " WHERE EmployeeId = " + employee.getEmployeeId();
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(updateStatement);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }

        return true;
    }
    public boolean Delete(Employee employee) throws SQLException {
        String deleteStatement = "DELETE FROM Employees WHERE EmployeeId = " + employee.getEmployeeId();
        try {
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
