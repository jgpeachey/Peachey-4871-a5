package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    InventoryCentral inv = new InventoryCentral();

    //tableview set up
    @FXML
    TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up file chooser
//        fileChooser.setInitialDirectory(new File("C:\\"));
        //set column value types
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));

        //load data from list
        tableView.setItems(inv.getDisplayList());
    }
    //misc methods
    public int getTableIndex(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        return index;
    }
}
