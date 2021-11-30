package DAO;

import Models.ItemSold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemsSoldDAO {
    private Connection connection;

    public ItemsSoldDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public boolean Create(ItemSold itemSold) throws SQLException {

        String createStatement = "INSERT INTO ItemsSold(ItemName, ItemPrice, OrderId) VALUES ('" +
                itemSold.getName() + "', '" +
                itemSold.getPrice() + "', '" +
                itemSold.getOrderId() + "')";

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
    public ObservableList<ItemSold> Read() throws SQLException {

        String createStatement = "SELECT * FROM ItemsSold";
        ObservableList<ItemSold> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new ItemSold(
                        queryResult.getInt("ItemId"),
                        queryResult.getString("ItemName"),
                        queryResult.getDouble("ItemPrice"),
                        queryResult.getInt("OrderId")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }

}
