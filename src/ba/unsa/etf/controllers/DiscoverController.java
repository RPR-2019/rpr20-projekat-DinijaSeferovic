package ba.unsa.etf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DiscoverController {

    @FXML
    private Button dogsButton;

    @FXML
    private Button catsButton;

    @FXML
    private Button bothButton;

    @FXML
    private VBox vboxDiscover;

    @FXML
    public void dogsDiscover(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dogsscreen.fxml"));
        myStage.setScene(new Scene(root, 875, 575));
        myStage.setTitle("Discover Dogs");
        myStage.show();
    }

    public void catsDiscover(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/catsscreen.fxml"));
        myStage.setScene(new Scene(root, 875, 575));
        myStage.setTitle("Discover Cats");
        myStage.show();
    }

    public void bothDiscover(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/bothscreen.fxml"));
        myStage.setScene(new Scene(root, 875, 575));
        myStage.setTitle("Discover Dogs and Cats");
        myStage.show();
    }

}
