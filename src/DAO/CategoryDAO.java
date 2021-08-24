package DAO;

import Models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Category category) throws SQLException {

        String createStatement = "INSERT INTO Category VALUES (" +
                category.getCategoryName() + ")";

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
    public List<Category> Read() throws SQLException {

        String createStatement = "SELECT * FROM Category";
        List<Category> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Category(queryResult.getString("Name")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return Collections.emptyList();
    }



}
