package ba.unsa.etf.controllers;

import ba.unsa.etf.dal.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Slider profileSlider;

    @FXML
    private RadioButton yesHomeRadio;

    @FXML
    private RadioButton noHomeRadio;

    @FXML
    private Spinner<Integer> profileSpinner;

    @FXML
    private RadioButton yesYardRadio;

    @FXML
    private RadioButton noYardRadio;

    @FXML
    private RadioButton yesPetsRadio;

    @FXML
    private RadioButton noPetsRadio;

    @FXML
    private ToggleGroup tgPets;

    @FXML
    private ToggleGroup tgYard;

    @FXML
    private ToggleGroup tgHome;

    private UserDAO user = UserDAO.getInstance();

    public void acceptAction(ActionEvent actionEvent) {
        if (yesHomeRadio.isSelected()) user.setHome(1);
        else if (noHomeRadio.isSelected()) user.setHome(0);
        if (yesPetsRadio.isSelected()) user.setPets(1);
        else if (noPetsRadio.isSelected()) user.setPets(0);
        if (yesYardRadio.isSelected()) user.setYard(1);
        else if (noYardRadio.isSelected()) user.setYard(0);
        //getting one decimal place from spinner
        user.setWork((double)Math.round(profileSlider.getValue() * 10d) / 10d);
        user.setRoomates(profileSpinner.getValue().intValue());
        user.setProfileSet(user.getCurrentUser());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
