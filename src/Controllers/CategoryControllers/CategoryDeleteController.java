package Controllers.CategoryControllers;

import Controllers.IController;
import Controllers.UpdateMenuController;
import DAO.CategoryDAO;
import Models.Category;
import Models.Credentials;
import Utilities.SceneSwitchUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryDeleteController implements IController {
    @FXML
    private Button DeleteButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Label CategoryLabel;
    private Category category;
    private Credentials credentials;

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setCategoryLabel(String categoryLabel) {
        CategoryLabel.setText(categoryLabel);
    }

    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws SQLException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.Delete(category);
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/Admin/UpdateMenuView.fxml",
                        CancelButton,
                        new UpdateMenuController(),
                        credentials,
                        null,
                        null
                );
    }
    @FXML
    public void CancelButtonOnAction() throws IOException
    {
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/Admin/UpdateMenuView.fxml",
                        CancelButton,
                        new UpdateMenuController(),
                        credentials,
                        null,
                        null
                );
    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
