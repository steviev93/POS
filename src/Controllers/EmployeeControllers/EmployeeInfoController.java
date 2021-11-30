package Controllers.EmployeeControllers;

import Controllers.IController;
import DAO.EmployeeDAO;
import Models.Credentials;
import Models.Employee;
import Utilities.SceneSwitchUtility;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable, IController {

    @FXML
    private TableView EmployeeTable;
    @FXML
    private TableColumn<Employee,Integer> IdCol;
    @FXML
    private TableColumn<Employee,String> NameCol;
    @FXML
    private TableColumn<Employee,String> PhoneCol;
    @FXML
    private TableColumn<Employee,String> EmailCol;
    @FXML
    private TableColumn<Employee, Date> SinceCol;
    @FXML
    private TableColumn<Employee,Float> WageCol;
    @FXML
    private TableColumn<Employee,Integer> TitleCol;
    @FXML
    private Button AddButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;
    private Credentials credentials;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            ObservableList<Employee> employeeData = employeeDAO.Read();
            System.out.println(employeeData.get(0));
            IdCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
            PhoneCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            SinceCol.setCellValueFactory(new PropertyValueFactory<>("EmployedSince"));
            WageCol.setCellValueFactory(new PropertyValueFactory<>("HourlySalary"));
            TitleCol.setCellValueFactory(new PropertyValueFactory<>("TitleId"));
            EmployeeTable.setItems(employeeData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void AddButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitchUtility sceneSwitch = new SceneSwitchUtility();
        sceneSwitch.SwitchScreen(sceneSwitch.LoadContent("Views/Admin/EmployeeViews/EmployeeViewCreate.fxml", AddButton), new EmployeeCreateController(), credentials);


    }
    @FXML
    public void UpdateButtonOnAction(ActionEvent event) throws IOException {
        Employee updateEmployee = (Employee)EmployeeTable.getSelectionModel().getSelectedItem();
        if (!(updateEmployee == null)) {
            Stage currentStage = (Stage) AddButton.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource(
                            "Views/Admin/EmployeeViews/EmployeeViewUpdate.fxml"
                    )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene((Parent) loader.load())
            );

            EmployeeUpdateController controller = loader.getController();

            controller.setUpdateEmployee(updateEmployee);

            stage.show();
        }
    }
    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) AddButton.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource(
                        "Views/Admin/EmployeeViews/EmployeeViewDelete.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene((Parent)loader.load())
        );

        EmployeeDeleteController controller = loader.getController();
        Employee empDelete = (Employee) EmployeeTable.getSelectionModel().getSelectedItem();

        controller.setEmployee(empDelete);
        controller.setEmployeeLabel(empDelete.getEmployeeName());

        stage.show();
    }
    @FXML
    public void CancelButtonOnAction() throws IOException {
        Stage currentStage = (Stage) AddButton.getScene().getWindow();
        currentStage.close();

    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
