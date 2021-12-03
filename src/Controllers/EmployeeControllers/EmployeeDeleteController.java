package Controllers.EmployeeControllers;

import Controllers.IController;
import DAO.EmployeeDAO;
import Models.Credentials;
import Models.Employee;
import Utilities.SceneSwitchUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeDeleteController implements IController {
    @FXML
    private Button DeleteButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Label EmployeeLabel;
    private Employee employee;
    private Credentials credentials;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setEmployeeLabel(String employeeLabel) {
        EmployeeLabel.setText(employeeLabel);
    }

    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws SQLException, IOException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.Delete(employee);
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/Admin/EmployeeViews/EmployeeInfoView.fxml",
                        CancelButton,
                        new EmployeeInfoController(),
                        credentials,
                        null,
                        null
                );
    }
    @FXML
    public void CancelButtonOnAction() throws IOException
    {
        Stage currentStage = (Stage) CancelButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
