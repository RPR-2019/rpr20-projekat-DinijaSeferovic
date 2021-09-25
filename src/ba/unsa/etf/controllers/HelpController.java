package ba.unsa.etf.controllers;

import ba.unsa.etf.dal.AdminDAO;
import ba.unsa.etf.dal.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;

    private UserDAO user = UserDAO.getInstance();
    private AdminDAO admin = AdminDAO.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user.getCurrentUser()!=null) {
            label1.setText("To start your process of finding and adopting a pet please fill in some information");
            label2.setText("about your lifestyle in the Profile section. According to that we will decide if we");
            label3.setText("approve the adoption based on the compatibility between your lifestyle and pets' habits.");
            label4.setText("You can see the decision for each liked pet. Search for your perfect companion in the ");
            label5.setText("Discover section and like pets that you want to meet in our shelter. After you got approved");
            label6.setText("for your liked pets, you can choose a date for your appointment in Progress section. We");
            label7.setText("are happy to meet you and introduce you to your possible future life companions.");
        }
        else if (admin.getCurrentAdmin()!=null) {
            label1.setText("In the Users section you can see all of the users in the system and delete them.");
            label2.setText("In the Schedule section you have an overview of all the users and their appointment dates.");
            label3.setText("It is possible to sort both users and dates by clicking on the column header.");
            label4.setText("In the Dogs and Cats sections you can find overviews of dogs and cats in the system.");
            label5.setText("In the overviews there are both adopted and non adopted pets and if a pet is adopted you");
            label6.setText("can find it there and change it's adoption status. There is also an option to delete");
            label7.setText("a pet from the system.");

        }
    }
}
