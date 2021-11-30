package DAO;

import Models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public boolean Create(Order order) throws SQLException {
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        String createStatement = "INSERT INTO Orders(OrderDT,Total,NumItems,EmployeeId) VALUES ('" +
                currentTime + "', '" +
                order.getSubtotal() + "', '" +
                order.getNumItems() + "', '" +
                order.getEmployeeId() + "')";

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
    public Integer ReadLast() throws SQLException {

        String getLastStatement = "SELECT OrderId FROM Orders WHERE OrderId=(SELECT MAX(OrderId) FROM Orders)";
        try {
            Statement lastLoginStatement = connection.createStatement();
            ResultSet lastLoginResult = lastLoginStatement.executeQuery(getLastStatement);
            lastLoginResult.next();

            int orderNo = lastLoginResult.getInt("OrderId");
            return orderNo;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return 1;
    }
    public ObservableList<Order> Read() throws SQLException {

        String createStatement = "SELECT * FROM Orders";
        ObservableList<Order> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Order(
                        queryResult.getInt("OrderId"),
                        queryResult.getDate("OrderDT"),
                        queryResult.getDouble("Total"),
                        queryResult.getInt("NumItems"),
                        queryResult.getInt("EmployeeId")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }
}
