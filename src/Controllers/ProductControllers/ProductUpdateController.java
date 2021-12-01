package Controllers.ProductControllers;

import Controllers.IController;
import DAO.ProductDAO;
import Models.Credentials;
import Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductUpdateController implements IController {
    @FXML
    private TextField ProductNameText;
    @FXML
    private TextField ProductPriceText;
    @FXML
    private Label CatId;
    @FXML
    private Button CancelButton;
    private Product newProduct;
    private ProductDAO productDAO;
    private Credentials credentials;


    public void setNewProduct(Product p) {
        newProduct = p;
        ProductNameText.setText(p.getName());
        ProductPriceText.setText(String.valueOf(p.getPrice()));
        CatId.setText(String.valueOf(p.getCategoryId()));
    }

    @FXML
    public void UpdateProductButtonOnAction() throws IOException, SQLException
    {
        if (ProductNameText.getText().length() > 3) {
            try {
                double price = Double.parseDouble(ProductPriceText.getText());
                productDAO = new ProductDAO();
                newProduct.setName(ProductNameText.getText());
                newProduct.setPrice(price);
                boolean result = productDAO.Update(newProduct);

                if (result) {
                    Stage currentStage = (Stage) CancelButton.getScene().getWindow();
                    currentStage.close();

                }
            } catch (Exception e) {
                System.out.println("Error");
            }
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
