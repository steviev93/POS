package Controllers.EmployeeControllers;

import Controllers.IController;
import DAO.EmployeeDAO;
import DAO.LoginDAO;
import DAO.TitleDAO;
import Models.Credentials;
import Models.Employee;
import Models.Title;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private Label validationLabel;
    private LoginDAO loginDAO;
    private EmployeeDAO employeeDAO;
    private TitleDAO titleDAO;
    private ObservableList<Title> titleList = FXCollections.observableArrayList();
    private List<Integer> pInd = new ArrayList<Integer>(List.of(1,2,3,8,9,10,14,15,16,17));
    private StringBuilder fNum = new StringBuilder("(###) - ### - ####");
    private String phoneText = "";
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
    }

    @FXML
    public void AddButtonOnAction() throws IOException, SQLException
    {
        if (!Validator.validatePassword(Password.getText())) validationLabel.setText("Invalid Password");
        String name = NameText.getText();
        String phone = PhoneText.getText();
        String email = EmailText.getText();
        Date since = new java.util.Date();
        float salary = 0.0f;
        try {
            salary = Float.parseFloat(SalaryText.getText());
        } catch (Exception e) {
            validationLabel.setText("Salary must be a float, numbers only");
            return;
        }

        String password = Password.getText();

        Integer titleId = Integer.parseInt(TitleCBox.getValue().toString().substring(0,1));
        Integer isAdmin = Integer.parseInt(isAdminCBox.getValue().toString().substring(0,1));

        Employee newEmployee = new Employee(-1, name,phone,email,since,salary,titleId);
        boolean result = employeeDAO.Create(newEmployee);
        Integer userNo = employeeDAO.ReadLast();
        System.out.println(userNo);
        boolean passResult = loginDAO.Create(String.valueOf(userNo),password,isAdmin);


        if (result && userNo != -1) {
            Stage currentStage = (Stage) CancelButton.getScene().getWindow();
            currentStage.close();

        }
    }

    @FXML
    public void CancelButtonOnAction() throws IOException {
        Stage currentStage = (Stage) CancelButton.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    public void phoneChanged() {

        if (Pattern.compile("[\\D]").matcher(PhoneText.getText()).find()) {
            validationLabel.setText("Phone number should be numbers only");
            PhoneText.setText(Pattern.compile("[\\D]").matcher(PhoneText.getText()).replaceAll(""));
        } else validationLabel.setText("");
        if (PhoneText.getText().length() >= 11) {
            PhoneText.setText(PhoneText.getText().substring(1));
        }
        PhoneText.positionCaret(PhoneText.getText().length());

    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
