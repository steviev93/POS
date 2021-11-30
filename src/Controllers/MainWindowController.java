package Controllers;

import DAO.*;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable, IController {

    private int orderNo;
    private int employeeNo;
    @FXML
    private TabPane MainPane;
    @FXML
    private TableView OrderedItems;
    @FXML
    private ObservableList<Product> ItemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn ItemColumn;
    @FXML
    private TableColumn PriceColumn;
    @FXML
    private Label SubtotalLabel;
    @FXML
    private Label TaxLabel;
    @FXML
    private Label TotalLabel;
    @FXML
    private Parent IncludedHeader;
    @FXML
    private HeaderController IncludedHeaderController;

    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private Credentials credentials;
    @Override
    public void loadCredentials(Credentials c)  {
        if (credentials == null) {
            this.credentials = c;
            IncludedHeaderController.loadCredentials(c);
            employeeNo = Integer.parseInt(credentials.getEmployeeId());
        }
    }


    @FXML
    public void SubmitOrderButtonOnAction(ActionEvent event) throws IOException, SQLException {
        ItemsSoldDAO itemsSoldDAO = new ItemsSoldDAO();

        Order order = new Order(-1,new java.util.Date(),Double.parseDouble(SubtotalLabel.getText().substring(1)),ItemList.size(),employeeNo);
        boolean result = orderDAO.Create(order);
        if (result) System.out.println("Success");
        else System.out.println("PROBLEM WITH ORDER INSERT");
        for (Product p: ItemList) {
            ItemSold itemSold = new ItemSold(-1, p.getName(),p.getPrice(),orderNo+1);
            boolean itemResult = itemsSoldDAO.Create(itemSold);
            if (itemResult) continue;
            else System.out.println("PROBLEM WITH ITEM INSERT");
        }
        ItemList.clear();
        CalculateTotals();
    }

    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws IOException {

        ObservableList<Product> selectedRows = OrderedItems.getSelectionModel().getSelectedItems();
        ArrayList<Product> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> OrderedItems.getItems().remove(row));
        CalculateTotals();
    }

    public void CalculateTotals() {
        Double subtotal = ItemList.stream()
                .mapToDouble(x -> x.getPrice())
                .sum();

        SubtotalLabel.setText(String.format("$%.2f", subtotal));
        TaxLabel.setText(String.format("$%.2f", subtotal*0.06));
        TotalLabel.setText(String.format("$%.2f", subtotal+subtotal*0.06));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            productDAO = new ProductDAO();
            orderDAO = new OrderDAO();
            categoryDAO = new CategoryDAO();

            orderNo = orderDAO.ReadLast();
            ItemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            OrderedItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            OrderedItems.setItems(ItemList);
            ObservableList<Product> productList = productDAO.Read();
            ObservableList<Category> categoryList = categoryDAO.Read();
            for (Category c : categoryList) {
                Tab newTab = new Tab();
                newTab.setText(c.getCategoryName());
                int catId = c.getIdCategory();
                ObservableList<Product> filtered = FXCollections.observableArrayList();
                filtered = productList.stream().filter(x -> x.getCategoryId() == catId).collect(Collectors.toCollection(FXCollections::observableArrayList));
                FlowPane flowpane = new FlowPane();
                flowpane.setHgap(20);
                flowpane.setVgap(20);
                flowpane.setPadding(new Insets(20, 20, 0, 20));
                flowpane.setAlignment(Pos.TOP_CENTER);
                for (Product p : filtered) {
                    Button button = new Button(p.getName());
                    button.setPrefWidth(120);
                    button.setPrefHeight(80);
                    button.setWrapText(true);
                    button.setTextAlignment(TextAlignment.CENTER);
                    button.setOnAction(e -> {
                        ItemList.add(p);
                        CalculateTotals();
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
}
