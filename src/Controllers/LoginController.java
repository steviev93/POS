package Controllers;

import DAO.LoginDAO;
import Models.Credentials;
import Utilities.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoginController implements IController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws InterruptedException, IOException {
        loginLabel.setText("");
        if (!Validator.validateID(username.getText())) {
            loginLabel.setText(loginLabel.getText() + "Employee ID must be a number only \n");
            return;
        }
        if (!Validator.validatePassword(password.getText())) {
            loginLabel.setText(loginLabel.getText() + "Invalid password \n");
            return;
        }
        LoginDAO loginDAO = null;
        try {
            loginDAO = new LoginDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Credentials result = loginDAO.validateLogin(username.getText(),password.getText());
        if (result.getEmployeeId() != "") {
            loginLabel.setText("Login Successful!");
            loginLabel.setVisible(true);
            TimeUnit.SECONDS.sleep(2);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource(
                            "Views/MainWindow1.fxml"
                    )
            );


            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene(loader.load())
            );
            MainWindowController mwc = loader.getController();
            mwc.loadCredentials(result);
            stage.setMaximized(true);
            stage.show();

        }
        else loginLabel.setText("Login Failed");
    }

    @Override
    public void loadCredentials(Credentials c) {
    }

}

