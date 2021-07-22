package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    InventoryCentral inv = new InventoryCentral();

    //tableview set up
    @FXML
    TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Set<String>> serialNumberColumn;
    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up file chooser
//        fileChooser.setInitialDirectory(new File("C:\\"));
        //set column value types
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, Set<String>>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));

        //load data from list
        tableView.setItems(inv.getDisplayList());
    }
    //misc methods
    public int getTableIndex(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        return index;
    }

    //set up interface for adding item
    interface OnItemAdded{
        void addItem(Item item);
    }

    //override addItem interface method
    OnItemAdded onItemAdded = new OnItemAdded() {
        @Override
        public void addItem(Item item) {
            list.add(item);
            inv.addItem(item);
            tableView.refresh();
        }
    };

    public void clickedNewItem(ActionEvent actionEvent) throws IOException {
        //open New Item Window
        FXMLLoader root = new  FXMLLoader(getClass().getResource("NewItemWindow.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("New Item");
        stage.setScene(new Scene(root.load()));
        //set up controller
        NewItemWindowController newController = root.getController();
        newController.setOnItemAdded(onItemAdded);
        //show window
        stage.show();
    }

    public void clickedRemoveItem(ActionEvent actionEvent) {
        // call remove item from Inventory Central
        //use the table index as a parameter
        inv.removeItem(getTableIndex());
    }

    public void clickedSaveInventory(ActionEvent actionEvent) {
    }

    public void clickedLoadInventory(ActionEvent actionEvent) {
    }

    public void clickedEditItem(ActionEvent actionEvent) {
    }
}
