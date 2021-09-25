package ba.unsa.etf.controllers;

import ba.unsa.etf.ItemButtonListener;
import ba.unsa.etf.dal.CatDAO;
import ba.unsa.etf.dal.DogDAO;
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

public class CatsController implements Initializable {
    private CatDAO dao = CatDAO.getInstance();

    @FXML
    private ScrollPane scrollCats;

    @FXML
    private GridPane gridCats;

    @FXML
    private GridPane mainGridCats;

    private List<CatDAO> cats = new ArrayList<>();
    private ItemButtonListener myListener;

    private void setHeartedCat(CatDAO cat) {
        dao.insertLikedCat(cats.stream().filter(c -> c.getId()==cat.getId()).findFirst().get());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        cats.addAll(dao.getAllUnadoptedCats());

        if (cats.size() > 0) {
            myListener = new ItemButtonListener() {
                @Override
                public void onClickListener(DogDAO dog) {}
                @Override
                public void onClickListener(CatDAO cat) {
                    setHeartedCat(cat);
                }
                @Override
                public void onClickListener(User user) {}
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < cats.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/griditem.fxml"));
                AnchorPane Pane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setCatData(cats.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridCats.add(Pane, column++, row); //(child,column,row)
                //set grid width
                gridCats.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridCats.setPrefWidth(833);
                gridCats.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridCats.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridCats.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridCats.setMaxHeight(Region.USE_PREF_SIZE);

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
