package Controllers;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Models.Category;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    private Map<Button, Product> actionRef = new HashMap<>();
    private int orderNo;
    @FXML
    private Button logoutButton;
    @FXML
    private Button DeleteButton;
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
    @FXML
    private TabPane MainPane;
    @FXML
    private TableView OrderedItems;
    @FXML
    private ObservableList<Product> Order = FXCollections.observableArrayList();
    @FXML
    private TableColumn ItemColumn;
    @FXML
    private TableColumn PriceColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ItemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            OrderedItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ProductDAO productDAO = new ProductDAO();
            ObservableList<Product> productList = productDAO.Read();
            CategoryDAO categoryDAO = new CategoryDAO();
            ObservableList<Category> categoryList = categoryDAO.Read();
            for (Category c : categoryList) {
                Tab newTab = new Tab();
                newTab.setText(c.getCategoryName());
                int catId = c.getIdCategory();
                ObservableList<Product> filtered = FXCollections.observableArrayList();
                filtered = productList.stream().filter(x->x.getCategoryId() == catId).collect(Collectors.toCollection(FXCollections::observableArrayList));

                FlowPane flowpane = new FlowPane();
                flowpane.setHgap(20);
                flowpane.setVgap(20);
                flowpane.setPadding(new Insets(20,0,0,0));
                for (Product p: filtered) {
                    Button button = new Button(p.getName());
                    button.setPrefWidth(120);
                    button.setPrefHeight(80);
                    button.setWrapText(true);
                    button.setOnAction(e->{
                        Order.add(p);
                        OrderedItems.setItems(Order);
                    });
                    flowpane.getChildren().add(button);
                }
                newTab.setContent(flowpane);
                MainPane.getTabs().add(newTab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws IOException {

        ObservableList<Product> selectedRows = OrderedItems.getSelectionModel().getSelectedItems();
        ArrayList<Product> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> OrderedItems.getItems().remove(row));
    }

}
