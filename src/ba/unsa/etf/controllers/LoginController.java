package ba.unsa.etf.controllers;


import ba.unsa.etf.dal.Admin;
import ba.unsa.etf.dal.AdminDAO;
import ba.unsa.etf.dal.User;
import ba.unsa.etf.dal.UserDAO;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.validator.EmailValidator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    private UserDAO user;
    private AdminDAO admin;
    private boolean validMail;
    @FXML
    private BorderPane layersignup;
    @FXML
    private Button signin;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private Label s4;
    @FXML
    private Button signup;
    @FXML
    private Label a2;
    @FXML
    private Label b2;
    @FXML
    private Label usernameRegInfo;
    @FXML
    private Label nameRegInfo;
    @FXML
    private Label emailRegInfo;
    @FXML
    private Label passRegInfo;
    @FXML
    private Label loginInfo;
    @FXML
    private Button btnsignup;
    @FXML
    private Button btnsignin;
    @FXML
    public BorderPane layer1;
    @FXML
    private AnchorPane layer2;
    @FXML
    private TextField nameRegField;
    @FXML
    private TextField usernameRegField;
    @FXML
    private TextField emailRegField;
    @FXML
    private TextField passRegField;
    @FXML
    private TextField usernameLogField;
    @FXML
    private TextField passLogField;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        s4.setVisible(false);
        signup.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(false);
        usernameLogField.setVisible(false);
        passLogField.setVisible(false);
        nameRegField.setVisible(true);
        usernameRegField.setVisible(true);
        emailRegField.setVisible(true);
        passRegField.setVisible(true);

        user = UserDAO.getInstance();
        admin = AdminDAO.getInstance();

        usernameRegField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validLength(n)) {
                    usernameRegField.getStyleClass().removeAll("fieldNotValid");
                    usernameRegField.getStyleClass().add("fieldValid");
                    usernameRegInfo.setText("");
                } else {
                    usernameRegField.getStyleClass().removeAll("fieldValid");
                    usernameRegField.getStyleClass().add("fieldNotValid");
                    usernameRegInfo.setText("Username must have more than 5 characters");
                }
            }
        });

        emailRegField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String o, String n) {
                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(n)) {
                    emailRegField.getStyleClass().removeAll("fieldNotValid");
                    emailRegField.getStyleClass().add("fieldValid");
                    emailRegInfo.setText("");
                    validMail = true;
                } else {
                    emailRegField.getStyleClass().removeAll("fieldValid");
                    emailRegField.getStyleClass().add("fieldNotValid");
                    emailRegInfo.setText("Please enter a valid email");
                    validMail = false;
                }
            }
        });

    }

    private boolean validLength(String n) {
        if (n.length() <= 5) return false;
        else return true;
    }

    private boolean validUniqueness(String username) {
        if (user.usernameExists(username) || admin.usernameExists(username)) return false;
        else return true;
    }

    private void settingVisibility(boolean visible) {
        btnsignin.setVisible(visible);
        b2.setVisible(visible);
        s1.setVisible(visible);
        s2.setVisible(visible);
        s3.setVisible(visible);
        s4.setVisible(visible);
        signup.setVisible(visible);
        l1.setVisible(!visible);
        l2.setVisible(!visible);
        l3.setVisible(!visible);
        l4.setVisible(!visible);
        signin.setVisible(!visible);
        a2.setVisible(!visible);
        btnsignup.setVisible(!visible);
        usernameLogField.setVisible(visible);
        passLogField.setVisible(visible);
        nameRegField.setVisible(!visible);
        usernameRegField.setVisible(!visible);
        emailRegField.setVisible(!visible);
        passRegField.setVisible(!visible);
    }


    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(layer2);

        slide.setToX(500);
        slide.play();

        layer1.setTranslateX(0);
        settingVisibility(true);

        slide.setOnFinished((e->{
        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        settingVisibility(false);

        slide.setOnFinished((e->{
        }));
    }

    private boolean usernameValidation() {
        if (!validUniqueness(usernameRegField.getText())) {
            usernameRegInfo.setText("This username already exists, please enter another one");
            usernameRegField.getStyleClass().removeAll("fieldValid");
            usernameRegField.getStyleClass().add("fieldNotValid");
            return false;
        }
        if (usernameRegField.getText().length()<=5) {
            usernameRegInfo.setText("Please enter an username that has more than 5 characters");
            return false;
        }
        return true;
    }

    private boolean nameValidation() {
        if (nameRegField.getText().length()<1) {
            nameRegInfo.setText("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean passValidation() {
        if (passRegField.getText().length()<1) {
            passRegInfo.setText("Please enter your password");
            return false;
        }
        return true;
    }

    private boolean emailValidation() {
        if (emailRegField.getText().length()<1) {
            emailRegInfo.setText("Please enter your email");
            return false;
        }
        if (validMail==false) {
            emailRegInfo.setText("Please enter a valid email");
            return false;
        }
        return true;
    }

    @FXML
    private void signupAction(ActionEvent event) {
        boolean possibleSignUp = true;
        if (usernameValidation()) {
            usernameRegInfo.setText("");
        }
        else possibleSignUp = false;

        if (nameValidation()) {
            nameRegInfo.setText("");
        }
        else possibleSignUp = false;

        if (passValidation()) {
            passRegInfo.setText("");
        }
        else possibleSignUp = false;

        if (emailValidation()) {
            emailRegInfo.setText("");
        }
        else possibleSignUp = false;

        if (possibleSignUp) {
            User userBean = new User(nameRegField.getText(),usernameRegField.getText(), emailRegField.getText(), passRegField.getText());
            user.registerUser(userBean);
            user.setCurrentUser(userBean);
            openMainUserScreen();
            closeCurrentWindow(event);
        }
    }

    @FXML
    private void signinAction(ActionEvent event) {
        if (user.usernameExists(usernameLogField.getText()) && user.getUserPassword(usernameLogField.getText()).equals(passLogField.getText())) {
            loginInfo.setText("");
            user.setCurrentUser(new User(usernameLogField.getText(), passLogField.getText()));
            openMainUserScreen();
            closeCurrentWindow(event);
        }
        else if (admin.usernameExists(usernameLogField.getText()) && admin.getUserPassword(usernameLogField.getText()).equals(passLogField.getText())) {
            loginInfo.setText("");
            admin.setCurrentAdmin(new Admin(usernameLogField.getText(), passLogField.getText()));
            openMainAdminScreen();
            closeCurrentWindow(event);
        }
        else if (passLogField.getText().length()<1) {
            passLogField.getStyleClass().removeAll("fieldValid");
            passLogField.getStyleClass().add("fieldNotValid");
            loginInfo.setText("Please enter your passsword");
        }
        else {
            loginInfo.setText("Sign In info is incorrect. Please change username or password");
        }
    }

    private void openMainUserScreen() {
        try {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainuserscreen.fxml"));
            newStage.setTitle("LoveFurEver Pet Shelter App");
            newStage.setScene(new Scene(root, 875, 575));
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openMainAdminScreen() {
        try {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainadminscreen.fxml"));
            newStage.setTitle("LoveFurEver Pet Shelter App");
            newStage.setScene(new Scene(root, 875, 575));
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeCurrentWindow(ActionEvent event) {
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }

}

