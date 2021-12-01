package Controllers;

import DAO.ClockDAO;
import DAO.LoginDAO;
import Models.Clock;
import Models.Credentials;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ClockController implements Initializable, IController {
    @FXML
    private Label ClockMessage;
    @FXML
    private Label LastClockTime;
    @FXML
    private Button ClockButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Label ValidationLabel;
    private boolean nextClockIn;
    private Credentials credentials;
    private Date PPS;
    private Date PPE;
    private Clock lastClock;
    private ClockDAO clockDAO;
    private LoginDAO loginDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void ClockButtonOnAction() throws SQLException, IOException, InterruptedException {
        if (!lastClock.wasClockin()) {
            clockDAO.clockIn(credentials.getEmployeeId(), lastClock);
        }
        else {
            clockDAO.clockOut(credentials.getEmployeeId(), lastClock);
        }
        ValidationLabel.setVisible(true);
        ValidationLabel.setText("Clock Successful");
        TimeUnit.SECONDS.sleep(1);
        Stage currentStage = (Stage) ClockButton.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    public void CancelButtonOnAction() throws IOException {
        Stage currentStage = (Stage) ClockButton.getScene().getWindow();
        currentStage.close();
    }
    @Override
    public void loadCredentials(Credentials c) {

        this.credentials = c;
        try {
            loginDAO = new LoginDAO();
            clockDAO = new ClockDAO();
            lastClock = clockDAO.lastClock(credentials.getEmployeeId());
            if (lastClock.getLastClock() != null) {
                if (lastClock.wasClockin() == true) {
                    ClockMessage.setText("Last Clock-In:");
                    LastClockTime.setText(lastClock.getLastClock().toString());
                    ClockButton.setText("Clock-Out");
                } else {
                    ClockMessage.setText("Last Clock-Out:");
                    LastClockTime.setText(lastClock.getLastClock().toString());
                    ClockButton.setText("Clock-In");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
