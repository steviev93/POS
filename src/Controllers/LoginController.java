package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import DAO.LoginDAO;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    public void loginButtonOnAction(ActionEvent event) {
        LoginDAO loginDAO = new LoginDAO();
        boolean result = loginDAO.validateLogin(username.getText(),password.getText());
        if (result) loginLabel.setText("Login Successful!");
        else loginLabel.setText("Login Failed");
    }

}

