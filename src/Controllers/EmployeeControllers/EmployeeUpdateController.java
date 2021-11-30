package Controllers.EmployeeControllers;

import Controllers.IController;
import DAO.EmployeeDAO;
import DAO.LoginDAO;
import DAO.TitleDAO;
import Models.Credentials;
import Models.Employee;
import Models.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeUpdateController implements Initializable, IController {
    @FXML
    private TextField NameText;
    @FXML
    private TextField PhoneText;
    @FXML
    private TextField EmailText;
    @FXML
    private TextField SalaryText;
    @FXML
    private PasswordField Password;
    @FXML
    private ChoiceBox TitleCBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox isAdminCBox = new ChoiceBox<>();
    @FXML
    private Button CancelButton;
    private LoginDAO loginDAO;
    private EmployeeDAO employeeDAO;
    private TitleDAO titleDAO;
    private Employee employee;
    private int isAdmin;
    private ObservableList<Title> titleList = FXCollections.observableArrayList();
    private Credentials credentials;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            loginDAO = new LoginDAO();
            employeeDAO = new EmployeeDAO();
            titleDAO = new TitleDAO();
            titleList = titleDAO.Read();
            for (Title t:titleList) {
                TitleCBox.getItems().add(t.getTitleId() + ": " + t.getTitleName());
            }
            isAdminCBox.getItems().add("0: No");
            isAdminCBox.getItems().add("1: Yes");
        } catch (SQLException e) {
            e.printStackTrace();
            e.getErrorCode();
        }
    }
    public void setUpdateEmployee(Employee e) {
        employee = e;
        isAdmin = credentials.isAdmin() ? 1 : 0;
        NameText.setText(employee.getEmployeeName());
        PhoneText.setText(employee.getPhoneNumber());
        EmailText.setText(employee.getEmail());
        SalaryText.setText(String.valueOf(employee.getHourlySalary()));
        System.out.println(titleList.get(employee.getTitleId()).getTitleName());
        TitleCBox.setValue(TitleCBox.getItems().get(employee.getTitleId()-1));
        isAdminCBox.setValue(isAdminCBox.getItems().get(isAdmin));

    }

    @FXML
    public void UpdateButtonOnAction() throws IOException, SQLException
    {
        String name = NameText.getText();
        String phone = PhoneText.getText();
        String email = EmailText.getText();
        Date since = new java.util.Date();
        float salary = Float.parseFloat(SalaryText.getText());
        Integer titleId = Integer.parseInt(TitleCBox.getValue().toString().substring(0,1));
        Integer isAdmin = Integer.parseInt(isAdminCBox.getValue().toString().substring(0,1));
        Employee newEmployee = new Employee(employee.getEmployeeId(), name,phone,email,since,salary,titleId);
        boolean result = employeeDAO.Update(newEmployee);
        boolean adminResult = loginDAO.UpdateIsAdmin(employee.getEmployeeId(), isAdmin);

        if (result ) {
            Stage currentStage = (Stage) CancelButton.getScene().getWindow();
            currentStage.close();

        }
    }

    @FXML
    public void CancelButtonOnAction() throws IOException {
        Stage currentStage = (Stage) CancelButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void loadCredentials(Credentials c) {
        credentials = c;
    }
}
