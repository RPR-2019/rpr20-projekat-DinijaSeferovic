package ba.unsa.etf.controllers;

import ba.unsa.etf.PrintReport;
import ba.unsa.etf.dal.User;
import ba.unsa.etf.dal.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
    @FXML
    private TableView<User> scheduleTable;
    public TableColumn<User,String> nameCol = new TableColumn<>("User");
    public TableColumn<User,String> dateCol = new TableColumn<>("Date");
    private User user=new User();
    private UserDAO userDao = UserDAO.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scheduleTable.setItems(user.getUsers());
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(160);
        dateCol.setCellValueFactory(new PropertyValueFactory("appointment"));
        dateCol.setMinWidth(160);
        scheduleTable.getColumns().addAll(nameCol, dateCol);
    }


    public void printReportAction(ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(userDao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

}
