package Controllers.ProductControllers;

import Controllers.IController;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Models.Category;
import Models.Credentials;
import Models.Product;
import Utilities.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    @FXML
    private Label validationLabelName;
    @FXML
    private Label validationLabelPrice;
    private ObservableList<Label> validators = FXCollections.observableArrayList();
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
        validators.add(validationLabelName);
        validators.add(validationLabelPrice);
    }

    @FXML
    public void AddProductButtonOnAction() throws IOException, SQLException
    {
        boolean verified = true;
        for (Label v : validators) {
            if (v.getText() != "") {
                verified = false;
                break;
            };
        }
        if (verified) {
            String name = ProductNameText.getText();
            Double price = 0.0d;
            try {
                price = Double.parseDouble(ProductPriceText.getText());
                Integer catId = Integer.parseInt(CatIdChoiceBox.getValue().toString().substring(0, 1));
                Product newProduct = new Product(-1, name, price, catId);
                boolean result = productDAO.Create(newProduct);

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

    @FXML
    public void nameChanged() {

    }
    @FXML
    public void priceChanged() {
        Validator.validateSalary(ProductPriceText, validationLabelPrice);
        ProductPriceText.positionCaret(ProductPriceText.getText().length());
        validators.set(1, validationLabelPrice);
    }

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
