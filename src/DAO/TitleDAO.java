package DAO;

import Models.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TitleDAO {
    private Connection connection;

    public TitleDAO() throws SQLException {
        DBConnector connector = new DBConnector();
        this.connection = connector.getConnection();
    }
    public ObservableList<Title> Read() throws SQLException {

        String readStatement = "SELECT * FROM Title";
        ObservableList<Title> result = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(readStatement);
            while(queryResult.next()) {
                result.add(new Title(
                        queryResult.getInt("TitleId"),
                        queryResult.getString("TitleName")));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return FXCollections.observableArrayList();
    }
}
