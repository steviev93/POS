package Utilities;

import Controllers.IController;
import Models.Credentials;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneSwitchUtility {
    private String newScenePath;
    private Button utilButton;
    private IController controller;
    private Credentials c;

    public SceneSwitchUtility(String newScenePath, Button utilButton, IController controller, Credentials c) {
        this.newScenePath = newScenePath;
        this.utilButton = utilButton;
        this.controller = controller;
        this.c = c;
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource(
                        newScenePath
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        controller = loader.getController();
        controller.loadCredentials(c);

        if(newScenePath=="Views/MainWindow1.fxml") stage.setMaximized(true);
        stage.show();
    }

    public FXMLLoader LoadContent(String newScenePath, Button utilButton) throws IOException {
        this.newScenePath = newScenePath;
        this.utilButton = utilButton;
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource(
                        newScenePath
                )
        );
        return loader;


    }

    public void SwitchScreen(FXMLLoader loader, IController controller, Credentials c) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        controller = loader.getController();
        controller.loadCredentials(c);

        if(newScenePath=="Views/MainWindow1.fxml") stage.setMaximized(true);
        stage.show();

    }
}
