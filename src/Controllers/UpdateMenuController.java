package Controllers;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Models.Category;
import Models.Product;
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
import java.util.ResourceBundle;

public class UpdateMenuController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button AddProductButton;
    @FXML
    private Button UpdateProductButton;
    @FXML
    private Button DeleteProductButton;
    @FXML
    private Button AddCategoryButton;
    @FXML
    private Button DeleteCategoryButton;
    @FXML
    private TableView ProductTable;
    @FXML
    private TableColumn<Product,Integer> ProductId;
    @FXML
    private TableColumn<Product,String> ProductName;
    @FXML
    private TableColumn<Product,Double> ProductPrice;
    @FXML
    private TableColumn<Product,Integer> ProductCategoryId;
    @FXML
    private TableView CategoryTable;
    @FXML
    private TableColumn<Category,Integer> CategoryId;
    @FXML
    private TableColumn<Category,Integer> CategoryName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ProductDAO productDAO = new ProductDAO();
            ObservableList<Product> productData = productDAO.Read();
            ProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
            ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            ProductCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            ProductTable.setItems(productData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            ObservableList<Category> categoryData = categoryDAO.Read();
            CategoryId.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
            CategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

            CategoryTable.setItems(categoryData);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource(
                        "Views/MainWindow.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );

        MainWindowController controller = loader.getController();
        controller.AdminMenu.setVisible(true);
        stage.show();
    }

    @FXML
    public void AddProductButtonOnAction() throws IOException{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Alerts/ProductViewCreate.fxml"));
        Stage stage = new Stage();
        Stage owner = (Stage) AddProductButton.getScene().getWindow();
        owner.close();
        Scene createProduct = new Scene(root);
        stage.setScene(createProduct);
        stage.show();
    }
    @FXML
    public void UpdateProductButtonOnAction() throws IOException{

    }
    @FXML
    public void DeleteProductButtonOnAction() throws IOException{

    }
    @FXML
    public void AddCategoryButtonOnAction() throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Alerts/CategoryViewCreate.fxml"));
        Stage stage = new Stage();
        Stage owner = (Stage) AddProductButton.getScene().getWindow();
        owner.close();
        Scene createProduct = new Scene(root);
        stage.setScene(createProduct);
        stage.show();
    }
    @FXML
    public void DeleteCategoryButtonOnAction() throws IOException{

    }


}
