package DAO;


import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {

    private Connection connection;

    public ProductDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Product product) throws SQLException {

        String createStatement = "INSERT INTO Items(Name,Price,categoryId) VALUES ('" +
            product.getName() + "', '" +
            product.getPrice() + "', '" +
            product.getCategoryId() + "')";

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
    public ObservableList<Product> Read() throws SQLException{

        String createStatement = "SELECT * FROM Items";
        ObservableList<Product> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Product(
                        queryResult.getInt("idItems"),
                        queryResult.getString("Name"),
                        queryResult.getDouble("Price"),
                        queryResult.getInt("CategoryId")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }

    public boolean Update(Product product) {
            String updateStatement = "UPDATE Items SET Name = " +
                    product.getName() + ", Price = " + product.getPrice() + " WHERE idItems = " + product.getId();
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


    public boolean Delete(ObservableList<Product> products) {
        for (Product p : products) {
            String deleteStatement = "DELETE FROM Items WHERE idItems = " + p.getId();
            try {
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(deleteStatement);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
                return false;
            }

        }
        return true;
    }

}
