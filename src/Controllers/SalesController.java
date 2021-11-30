package Controllers;

import Models.Credentials;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SalesController implements IController {
    @FXML
    private ChoiceBox TypeCBox;
    @FXML
    private ChoiceBox TimeCBox;
    @FXML
    private Button GraphButton;
    @FXML
    private LineChart SalesLine;
    private Credentials credentials;

    @Override
    public void loadCredentials(Credentials c) {

        credentials = c;
    }
}
