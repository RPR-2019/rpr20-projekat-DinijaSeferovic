package ba.unsa.etf;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FxmlScreenLoader {

    private Pane view;

    public Pane getPage (String fileName) throws IOException {

        try {
            URL fileUrl = Main.class.getResource("/fxml/" + fileName + ".fxml");
            if (fileName == null) {
                throw new java.io.FileNotFoundException("FXML file cannot be found");
            }

            view = new FXMLLoader().load(fileUrl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }
}
