package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Map<String, Double> menu = new HashMap<>();
    private int orderNo;
    @FXML
    private Button logoutButton;
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
    private ButtonBar ButtonBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void logoutButtonOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/LoginView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene loginWindow = new Scene(root);
        stage.setScene(loginWindow);
        stage.show();
    }
    @FXML
    public void UpdateMenuButtonOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/UpdateMenuView.fxml"));
        Stage stage = (Stage)ButtonBar.getScene().getWindow();
        Scene loginWindow = new Scene(root);
        stage.setScene(loginWindow);
        stage.show();
    }
    @FXML
    public void EmployeeInfoButtonOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/EmployeeInfoView.fxml"));
        Stage stage = (Stage)ButtonBar.getScene().getWindow();
        Scene loginWindow = new Scene(root);
        stage.setScene(loginWindow);
        stage.show();
    }
    @FXML
    public void InventoryMgmtButtonOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/InventoryMgmtView.fxml"));
        Stage stage = (Stage)ButtonBar.getScene().getWindow();
        Scene loginWindow = new Scene(root);
        stage.setScene(loginWindow);
        stage.show();
    }
    @FXML
    public void SalesButtonOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/SalesView.fxml"));
        Stage stage = (Stage)ButtonBar.getScene().getWindow();
        Scene loginWindow = new Scene(root);
        stage.setScene(loginWindow);
        stage.show();
    }

}
