package Controllers;

import Controllers.EmployeeControllers.EmployeeInfoController;
import DAO.LoginDAO;
import Models.Credentials;
import Utilities.SceneSwitchUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HeaderController implements Initializable, IController {
    @FXML
    public MenuButton AdminMenu;
    @FXML
    private MenuItem UpdateMenuButton;
    @FXML
    private MenuItem EmployeeInfoButton;
    @FXML
    private MenuItem InventoryMgmtButton;
    @FXML
    private MenuItem SalesButton;
    @FXML
    private MenuItem ClockButton;
    @FXML
    private MenuItem ScheduleButton;
    @FXML
    private MenuItem HistoryButton;
    @FXML
    private Button logoutButton;
    private LoginDAO loginDAO;
    private Credentials credentials;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginDAO = new LoginDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        credentials = null;
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.close();
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/LoginView.fxml",
                        logoutButton,
                        new UpdateMenuController(),
                        credentials,
                        null,
                        null
                );

    }
    @FXML
    public void UpdateMenuButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/Admin/UpdateMenuView.fxml",
                        logoutButton,
                        new UpdateMenuController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
        );
    }
    @FXML
    public void EmployeeInfoButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/Admin/EmployeeViews/EmployeeInfoView.fxml",
                        logoutButton,
                        new EmployeeInfoController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
        );
    }
    @FXML
    public void InventoryMgmtButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/Admin/InventoryMgmtView.fxml",
                        logoutButton,
                        new InventoryMgmtController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
        );
    }
    @FXML
    public void SalesButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/Admin/SalesView.fxml",
                        logoutButton,
                        new SalesController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
                );
    }
    @FXML
    public void ClockButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/ClockView.fxml",
                        logoutButton,
                        new ClockController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
                );
    }
    @FXML
    public void ScheduleButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/ClockView.fxml",
                        logoutButton,
                        new ClockController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
        );
    }
    @FXML
    public void HistoryButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/ClockView.fxml",
                        logoutButton,
                        new ClockController(),
                        credentials,
                        "Views/MainWindow1.fxml",
                        new MainWindowController()
        );
    }

    @Override
    public void loadCredentials(Credentials c) {
        this.credentials = c;
        if(credentials.isAdmin()) AdminMenu.setVisible(true);
        else AdminMenu.setVisible(false);
    }
}
