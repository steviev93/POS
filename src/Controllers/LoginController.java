package Controllers;

import DAO.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;

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
    public void loginButtonOnAction(ActionEvent event) throws InterruptedException {
        LoginDAO loginDAO = new LoginDAO();
        boolean result = loginDAO.validateLogin(username.getText(),password.getText());
        if (result) {
            loginLabel.setText("Login Successful!");
            TimeUnit.SECONDS.sleep(3);


        }
        else loginLabel.setText("Login Failed");
    }

}

