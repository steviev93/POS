package Controllers;

import DAO.ProductDAO;
import Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class ProductController {
    @FXML
    private TextField ProductNameText;
    @FXML
    private TextField ProductPriceText;
    @FXML
    private TextField ProductCategoryIdText;
    @FXML
    private Button AddProductButton;
    @FXML
    private Button CancelButton;

    @FXML
    public void AddProductButtonOnAction() throws SQLException, IOException
    {
        ProductDAO productDAO = new ProductDAO();
        String name = ProductNameText.getText();
        Double price = Double.parseDouble(ProductPriceText.getText());
        Integer catId = Integer.parseInt(ProductCategoryIdText.getText());
        Product newProduct = new Product(-1, name,price,catId);
        boolean result = productDAO.Create(newProduct);

        if (result) {
            ((Stage)AddProductButton.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Admin/UpdateMenuView.fxml"));
            Stage stage = new Stage();
            Scene mainWindow = new Scene(root);
            stage.setScene(mainWindow);
            stage.show();
        }



    }

}
