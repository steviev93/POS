package Controllers.ProductControllers;

import Controllers.IController;
import Controllers.UpdateMenuController;
import DAO.ProductDAO;
import Models.Credentials;
import Models.Product;
import Utilities.SceneSwitchUtility;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductDeleteController implements IController {
    @FXML
    private Button DeleteButton;
    @FXML
    private Button CancelButton;
    @FXML
    private ListView DeleteList = new ListView<String>();
    private ObservableList<Product> Products;
    private Credentials credentials;


    public void setDeleteList(ObservableList<String> products) {
        DeleteList.setItems(products);
    }

    public ObservableList<Product> getProducts() {
        return Products;
    }

    public void setProducts(ObservableList<Product> products) {
        Products = products;
    }

    @FXML
    public void DeleteButtonOnAction(ActionEvent event) throws SQLException, IOException {
        ProductDAO productDAO = new ProductDAO();
        productDAO.Delete(Products);
        SceneSwitchUtility sceneSwitch =
                new SceneSwitchUtility(
                        "Views/Admin/UpdateMenuView.fxml",
                        CancelButton,
                        new UpdateMenuController(),
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
