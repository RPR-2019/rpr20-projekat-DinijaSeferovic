package ba.unsa.etf.controllers;

import ba.unsa.etf.ItemButtonListener;
import ba.unsa.etf.dal.CatDAO;
import ba.unsa.etf.dal.DogDAO;
import ba.unsa.etf.dal.Pet;
import ba.unsa.etf.dal.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BothController implements Initializable {
    private CatDAO cat = CatDAO.getInstance();
    private DogDAO dog = DogDAO.getInstance();

    @FXML
    private ScrollPane scrollBoth;

    @FXML
    private GridPane gridBoth;

    @FXML
    private GridPane mainGridBoth;

    private List<Pet> pets = new ArrayList<>();
    private ItemButtonListener myListener;

    private void setHeartedPet(Pet pet) {
        if (pet instanceof DogDAO) {
            dog.insertLikedDog((DogDAO) pets.stream().filter(d -> d.getId()==pet.getId()).findFirst().get());
        }
        if (pet instanceof CatDAO) {
            cat.insertLikedCat((CatDAO) pets.stream().filter(d -> d.getId()==pet.getId()).findFirst().get());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        pets.addAll(dog.getAllDogs());
        pets.addAll(cat.getAllCats());

        if (pets.size() > 0) {
            myListener = new ItemButtonListener() {
                @Override
                public void onClickListener(DogDAO dog) {
                    setHeartedPet(dog);
                }
                @Override
                public void onClickListener(CatDAO cat) {
                    setHeartedPet(cat);
                }
                @Override
                public void onClickListener(User user) {}
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < pets.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/griditem.fxml"));
                AnchorPane Pane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                if (pets.get(i) instanceof CatDAO) {
                    itemController.setCatData((CatDAO) pets.get(i), myListener);
                }
                else if (pets.get(i) instanceof DogDAO) {
                    itemController.setDogData((DogDAO) pets.get(i), myListener);
                }


                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridBoth.add(Pane, column++, row); //(child,column,row)
                //set grid width
                gridBoth.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridBoth.setPrefWidth(833);
                gridBoth.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridBoth.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridBoth.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridBoth.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(Pane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void goBack(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
