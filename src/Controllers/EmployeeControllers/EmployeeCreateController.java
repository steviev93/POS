package Controllers.EmployeeControllers;

import Controllers.IController;
import DAO.EmployeeDAO;
import DAO.LoginDAO;
import DAO.TitleDAO;
import Models.Credentials;
import Models.Employee;
import Models.Title;
import Utilities.SceneSwitchUtility;
import Utilities.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeCreateController implements Initializable, IController {
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
    @FXML
    private Label validationLabelName;
    @FXML
    private Label validationLabelPhone;
    @FXML
    private Label validationLabelEmail;
    @FXML
    private Label validationLabelSalary;
    @FXML
    private Label validationLabelPassword;
    private ObservableList<Label> validators = FXCollections.observableArrayList();

    private LoginDAO loginDAO;
    private EmployeeDAO employeeDAO;
    private TitleDAO titleDAO;
    private ObservableList<Title> titleList = FXCollections.observableArrayList();
    private int i = 0;
    private Credentials credentials;
    private List<String> p = FXCollections.observableArrayList();

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
        validators.add(validationLabelName);
        validators.add(validationLabelPhone);
        validators.add(validationLabelSalary);
        validators.add(validationLabelEmail);
    }

    @FXML
    public void AddButtonOnAction() throws IOException, SQLException
    {
        boolean verified = true;
        for (Label v : validators) {
            if (v.getText() != "") {
                verified = false;
                break;
            };
        }
        if (verified) {
            String name = NameText.getText();
            String phone = PhoneText.getText();
            String email = EmailText.getText();
            Date since = new java.util.Date();
            float salary = Float.parseFloat(SalaryText.getText());


            String password = Password.getText();

            Integer titleId = Integer.parseInt(TitleCBox.getValue().toString().substring(0, 1));
            Integer isAdmin = Integer.parseInt(isAdminCBox.getValue().toString().substring(0, 1));
            Employee newEmployee;
            try {
                newEmployee  = new Employee(-1, name, phone, email, since, salary, titleId);
                boolean result = employeeDAO.Create(newEmployee);
                Integer userNo = employeeDAO.ReadLast();
                System.out.println(userNo);
                boolean passResult = loginDAO.Create(String.valueOf(userNo), password, isAdmin);


                if (result && userNo != -1) {
                    Stage currentStage = (Stage) CancelButton.getScene().getWindow();
                    currentStage.close();

                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    public void CancelButtonOnAction() throws IOException {
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
    public void phoneChanged() {

        Validator.validatePhone(PhoneText, validationLabelPhone);
        PhoneText.positionCaret(PhoneText.getText().length());
        validators.set(1, validationLabelPhone);


    }
    @FXML
    public void emailChanged() {

        Validator.validateEmail(EmailText, validationLabelEmail);
        EmailText.positionCaret(EmailText.getText().length());
        validators.set(3, validationLabelEmail);


    }
    @FXML
    public void salaryChanged() {

        Validator.validateSalary(SalaryText, validationLabelSalary);
        SalaryText.positionCaret(SalaryText.getText().length());
        validators.set(2, validationLabelSalary);


    }
    @FXML
    public void passwordChanged() {

        Validator.validatePassword(Password.getText(), validationLabelPassword);
        SalaryText.positionCaret(SalaryText.getText().length());

    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
