package DAO;

import Models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Category category) throws SQLException {

        String createStatement = "INSERT INTO Category(Name) VALUES ('" +
                category.getCategoryName() + "')";

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
    public ObservableList<Category> Read() throws SQLException {

        String createStatement = "SELECT * FROM Category";
        ObservableList<Category> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(createStatement);
            while(queryResult.next()) {
                result.add(new Category(
                        queryResult.getInt("idCategory"),
                        queryResult.getString("Name")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }



}
