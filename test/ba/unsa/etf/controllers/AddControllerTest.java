package ba.unsa.etf.controllers;

import static org.junit.jupiter.api.Assertions.*;

import ba.unsa.etf.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
@ExtendWith(ApplicationExtension.class)

class AddControllerTest {

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/addscreen.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

}