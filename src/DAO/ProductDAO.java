package DAO;


import Models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Product product) throws SQLException {

        String createStatement = "INSERT INTO Items VALUES (" +
            product.getName() + ", " +
            product.getPrice() + ", " +
            product.getCategoryId() + ")";

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
    public List<Product> Read() throws SQLException{

        String createStatement = "SELECT * FROM Items";
        List<Product> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Product(queryResult.getString("Name"),
                        queryResult.getDouble("Price"),
                        queryResult.getInt("CategoryId")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return Collections.emptyList();
    }


}
