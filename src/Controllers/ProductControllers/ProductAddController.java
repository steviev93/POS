package Controllers.ProductControllers;

import Controllers.IController;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Models.Category;
import Models.Credentials;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ProductAddController implements Initializable, IController {
    @FXML
    private TextField ProductNameText;
    @FXML
    private TextField ProductPriceText;
    @FXML
    private ChoiceBox CatIdChoiceBox = new ChoiceBox<>();
    @FXML
    private Button CancelButton;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private ObservableList<Category> categoryList = FXCollections.observableArrayList();
    private Credentials credentials;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            productDAO = new ProductDAO();
            categoryDAO = new CategoryDAO();
            categoryList = categoryDAO.Read();
            for (Category c:categoryList) {
                CatIdChoiceBox.getItems().add(c.getIdCategory() + ": " + c.getCategoryName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getErrorCode();
        }
    }

    @FXML
    public void AddProductButtonOnAction() throws IOException, SQLException
    {
        String name = ProductNameText.getText();
        Double price = 0.0d;
        try {
            Double.parseDouble(ProductPriceText.getText());
            Integer catId = Integer.parseInt(CatIdChoiceBox.getValue().toString().substring(0,1));
            Product newProduct = new Product(-1, name,price,catId);
            boolean result = productDAO.Create(newProduct);

            if (result) {
                Stage currentStage = (Stage) CancelButton.getScene().getWindow();
                currentStage.close();
            }
        } catch (Exception e) {
            System.out.println("Error");
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
