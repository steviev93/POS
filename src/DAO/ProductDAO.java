package DAO;


import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductDAO {

    private Connection connection;

    public ProductDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Product product) throws SQLException {

        String createStatement = "INSERT INTO Items(Name,Price,categoryId) VALUES (?, ?, ?)";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(createStatement);
            prepStmt.setString(1, product.getName());
            prepStmt.setFloat(2, (float)product.getPrice());
            prepStmt.setInt(3, product.getId());
            prepStmt.executeUpdate();
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
            String updateStatement = "UPDATE Items SET Name = ?, Price = ? WHERE idItems = ?";
            try {
                PreparedStatement prepStmt = connection.prepareStatement(updateStatement);
                prepStmt.setString(1, product.getName());
                prepStmt.setFloat(2, (float)product.getPrice());
                prepStmt.setInt(3, product.getId());
                prepStmt.executeUpdate();

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
