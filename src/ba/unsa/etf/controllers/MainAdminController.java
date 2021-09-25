package ba.unsa.etf.controllers;

import ba.unsa.etf.FxmlScreenLoader;
import ba.unsa.etf.dal.AdminDAO;
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

public class MainAdminController implements Initializable {

    @FXML
    private BorderPane mainPaneAdmin;
    private AdminDAO admin = AdminDAO.getInstance();

    @FXML
    private void handleButtonLogout2(ActionEvent event) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginscreen.fxml"));
        myStage.setScene(new Scene(root, 875, 575));
        myStage.setTitle("Welcome to Love FurEver");
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        myStage.show();
        admin.setCurrentAdmin(null);
    }

    @FXML
    private void handleButtonHelp2(ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("helpscreen");
        mainPaneAdmin.setCenter(view);
    }

    @FXML
    private void handleButtonUsers(ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("usersscreen");
        mainPaneAdmin.setCenter(view);
    }

    @FXML
    private void handleButtonSchedule(ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("schedulescreen");
        mainPaneAdmin.setCenter(view);
    }

    @FXML
    private void handleButtonDogs(ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("admindogsscreen");
        mainPaneAdmin.setCenter(view);
    }

    @FXML
    private void handleButtonCats(ActionEvent event) throws IOException {
        FxmlScreenLoader object = new FxmlScreenLoader();
        Pane view = object.getPage("admincatsscreen");
        mainPaneAdmin.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
