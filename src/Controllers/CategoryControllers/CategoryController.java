package Controllers.CategoryControllers;

import Controllers.IController;
import Controllers.UpdateMenuController;
import DAO.CategoryDAO;
import Models.Category;
import Models.Credentials;
import Utilities.SceneSwitchUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryController implements IController {
    @FXML
    private TextField CategoryNameText;
    @FXML
    private Button AddCategoryButton;
    @FXML
    private Button CancelButton;
    private Credentials credentials;

    @FXML
    public void AddCategoryButtonOnAction() throws SQLException, IOException
    {
        if (CategoryNameText.getText().length() > 4) {
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
        } else System.out.println("Category name must be 4 characters long!");

    }
    @FXML
    public void CancelButtonOnAction() throws IOException
    {
        SceneSwitchUtility sceneSwitch = new SceneSwitchUtility();
        sceneSwitch.SwitchScreen(sceneSwitch.LoadContent("Views/Admin/UpdateMenuView.fxml", CancelButton), new UpdateMenuController(), credentials);

    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
