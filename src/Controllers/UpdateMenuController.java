package Controllers;

import Controllers.CategoryControllers.CategoryController;
import Controllers.CategoryControllers.CategoryDeleteController;
import Controllers.ProductControllers.ProductAddController;
import Controllers.ProductControllers.ProductDeleteController;
import Controllers.ProductControllers.ProductUpdateController;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Models.Category;
import Models.Credentials;
import Models.Product;
import Utilities.SceneSwitchUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateMenuController implements Initializable, IController {
    @FXML
    private Button backButton;
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
    private Credentials credentials;


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
            ProductTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/MainWindow1.fxml",
                        backButton,
                        new ProductAddController(),
                        credentials,
                        null,
                        null
                );
    }

    @FXML
    public void AddProductButtonOnAction() throws IOException{
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                "Views/Admin/ProductViews/ProductViewCreate.fxml",
                        backButton,
                        new ProductAddController(),
                        credentials,
                        "Views/Admin/UpdateMenuView.fxml",
                        new UpdateMenuController()
        );

    }
    @FXML
    public void UpdateProductButtonOnAction() throws IOException{
        Product updateProduct = (Product)ProductTable.getSelectionModel().getSelectedItem();
        if (updateProduct != null) {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource(
                            "Views/Admin/ProductViews/ProductViewUpdate.fxml"
                    )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene((Parent) loader.load())
            );

            ProductUpdateController controller = loader.getController();

            controller.setNewProduct(updateProduct);

            stage.showAndWait();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
            SceneSwitchUtility sceneSwitch =
                    new SceneSwitchUtility(
                            "Views/Admin/UpdateMenuView.fxml",
                            backButton,
                            new UpdateMenuController(),
                            credentials,
                            null,
                            null
                    );

        }
    }
    @FXML
    public void DeleteProductButtonOnAction() throws IOException{
        if (ProductTable.getSelectionModel().getSelectedItems().get(0) != null) {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource(
                            "Views/Admin/ProductViews/ProductViewDelete.fxml"
                    )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene((Parent) loader.load())
            );

            ProductDeleteController controller = loader.getController();
            controller.setProducts(ProductTable.getSelectionModel().getSelectedItems());
            ObservableList<String> deleteProducts = FXCollections.observableArrayList();
            for (Product p : (ObservableList<Product>) ProductTable.getSelectionModel().getSelectedItems()) {
                deleteProducts.add(p.getName());
            }
            controller.setDeleteList(deleteProducts);

            stage.showAndWait();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
            SceneSwitchUtility sceneSwitch =
                    new SceneSwitchUtility(
                            "Views/Admin/UpdateMenuView.fxml",
                            backButton,
                            new UpdateMenuController(),
                            credentials,
                            null,
                            null
                    );
        }
    }
    @FXML
    public void AddCategoryButtonOnAction() throws IOException{
        SceneSwitchUtility sceneSwitch = new SceneSwitchUtility(
                "Views/Admin/CategoryViews/CategoryViewCreate.fxml",
                backButton,
                new CategoryController(),
                credentials,
                "Views/Admin/UpdateMenuView.fxml",
                new UpdateMenuController()
        );
    }
    @FXML
    public void DeleteCategoryButtonOnAction() throws IOException{
        Category catDelete = (Category) CategoryTable.getSelectionModel().getSelectedItem();
        if (catDelete != null) {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource(
                            "Views/Admin/CategoryViews/CategoryViewDelete.fxml"
                    )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene((Parent) loader.load())
            );

            CategoryDeleteController controller = loader.getController();

            controller.setCategory(catDelete);
            controller.setCategoryLabel(catDelete.getCategoryName());

            stage.showAndWait();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
            SceneSwitchUtility sceneSwitch =
                    new SceneSwitchUtility(
                            "Views/Admin/UpdateMenuView.fxml",
                            backButton,
                            new UpdateMenuController(),
                            credentials,
                            null,
                            null
                    );
        }
    }


    @Override
    public void loadCredentials(Credentials c) {
        this.credentials = c;
    }
}
