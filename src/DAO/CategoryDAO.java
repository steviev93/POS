package DAO;

import Models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }


    public boolean Create(Category category) throws SQLException {

        String createStatement = "INSERT INTO Category(Name) VALUES (?)";
        PreparedStatement prepStmt = connection.prepareStatement(createStatement);

        try {
            prepStmt.setString(1, category.getCategoryName());
            prepStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }
    public ObservableList<Category> Read() throws SQLException {

        String readStatement = "SELECT * FROM Category";
        ObservableList<Category> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(readStatement);
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

    public boolean Delete(Category category) throws SQLException {
        String sql = "DELETE FROM Category WHERE idCategory = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        try {
            prepStmt.setInt(1, category.getIdCategory());
            prepStmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }



}
