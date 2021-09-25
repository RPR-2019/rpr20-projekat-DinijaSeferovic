package ba.unsa.etf.controllers;

import ba.unsa.etf.FxmlScreenLoader;
import ba.unsa.etf.dal.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUserController implements Initializable {

    @FXML
    private BorderPane mainPaneUser;
    private UserDAO user = UserDAO.getInstance();


    @FXML
    private void handleButtonProfile(javafx.event.ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("profilescreen");
        mainPaneUser.setCenter(view);
    }

    @FXML
    private void handleButtonDiscover(javafx.event.ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("discoverscreen");
        mainPaneUser.setCenter(view);
    }

    @FXML
    private void handleButtonLiked(javafx.event.ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("likedscreen");
        mainPaneUser.setCenter(view);
    }

    @FXML
    private void handleButtonProgress(javafx.event.ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("progressscreen");
        mainPaneUser.setCenter(view);
    }

    @FXML
    private void handleButtonLogout1(javafx.event.ActionEvent event) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginscreen.fxml"));
        myStage.setScene(new Scene(root, 875, 575));
        myStage.setTitle("Welcome to Love FurEver");
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        myStage.show();
        user.setCurrentUser(null);
    }

    @FXML
    private void handleButtonHelp1(javafx.event.ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("helpscreen");
        mainPaneUser.setCenter(view);

    }

    public void handleButtonAdd(ActionEvent actionEvent) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("addscreen");
        mainPaneUser.setCenter(view);
    }

    public void handleButtonCare(ActionEvent actionEvent) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("carescreen");
        mainPaneUser.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
