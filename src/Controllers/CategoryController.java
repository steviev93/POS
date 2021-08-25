package Controllers;

import DAO.CategoryDAO;
import Models.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryController {
    @FXML
    private TextField CategoryNameText;
    @FXML
    private Button AddCategoryButton;
    @FXML
    private Button CancelButton;

    @FXML
    public void AddCategoryButtonOnAction() throws SQLException, IOException
    {
        CategoryDAO categoryDAO = new CategoryDAO();
        String name = CategoryNameText.getText();
        Category newCategory = new Category(-1, name);
        boolean result = categoryDAO.Create(newCategory);
        if (result) {
            ((Stage)AddCategoryButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/UpdateMenuView.fxml"));
            Stage stage = new Stage();
            Scene mainWindow = new Scene(root);
            stage.setScene(mainWindow);
            stage.show();
        }
    }
    @FXML
    public void CancelButtonOnAction() throws IOException
    {
        ((Stage)AddCategoryButton.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/UpdateMenuView.fxml"));
        Stage stage = new Stage();
        Scene mainWindow = new Scene(root);
        stage.setScene(mainWindow);
        stage.show();
    }
}
