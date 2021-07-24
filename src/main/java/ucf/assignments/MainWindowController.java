package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    InventoryCentral inv = new InventoryCentral();
    FileCentral file = new FileCentral();

    //file chooser setup
    FileChooser fileChooser = new FileChooser();

    //tableview set up
    @FXML
    TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Set<String>> serialNumberColumn;
    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    //Search bar set up
    @FXML
    private TextField searchTextField;

    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up file chooser
        fileChooser.setInitialDirectory(new File("C:\\"));
        //set column value types
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, Set<String>>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));

        //more set up for search bar
        FilteredList<Item> searchBar = new FilteredList<>(inv.getDisplayList(), b -> true);
        //set up search function
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchBar.setPredicate(item -> {
                //if empty display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //compare name and serial number to every item
                String upperCaseFilter = newValue.toUpperCase();

                //if search bar matches name
                if (item.getName().toUpperCase().indexOf(upperCaseFilter) != -1 ) {
                    return true;
                }
                //if search bar matches serialNumber
                else if (item.getSerialNumber().indexOf(upperCaseFilter) != -1) {
                    return true;
                }
                //doesnt match
                else {
                    return false;
                }
            });
        });
        //put filtered list in sorted list
        SortedList<Item> searchData = new SortedList<>(searchBar);

        //bind sorted list to table view
        searchData.comparatorProperty().bind(tableView.comparatorProperty());

        //load data into table view
        tableView.setItems(searchData);
    }
//    misc methods
    public int getTableIndex(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        return index;
    }

//    interfaces and corresponding methods
    //set up interface for adding item
    interface OnItemEdited{
        void editItem(String name, BigDecimal value, String serialNumber);
    }

    //override editItem interface method
    OnItemEdited onItemEdited = new OnItemEdited() {
        @Override
        public void editItem(String name, BigDecimal value, String serialNumber) {
            inv.editItemInfo(getTableIndex(), name, value, serialNumber);
            tableView.refresh();
        }
    };

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

    //set up interface for isUnique
    interface OnIsUnique{
        boolean isUnique(String serialNumber);
    }

    //override addItem interface method
    OnIsUnique onIsUnique = new OnIsUnique() {
        @Override
        public boolean isUnique(String serialNumber) {
            boolean unique = true;
            if (inv.getWholeList() != null) {
                for (int i = 0; i < inv.getWholeList().size(); i++) {
                    String otherSerialNumber = inv.getWholeList().get(i).getSerialNumber();
                    if (serialNumber.equalsIgnoreCase(otherSerialNumber)) {
                        if (getTableIndex() == i){
                            unique = true;
                            break;
                        }
                        unique = false;
                    }
                }
            }
            return unique;
        }
    };

    @FXML
    public void clickedNewItem(ActionEvent actionEvent) throws IOException {
        //open New Item Window
        FXMLLoader root = new  FXMLLoader(getClass().getResource("NewItemWindow.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("New Item");
        stage.setScene(new Scene(root.load()));
        //set up add controller
        NewItemWindowController newController = root.getController();
        newController.setOnItemAdded(onItemAdded);
        //set up unique controller
        NewItemWindowController uniqueController = root.getController();
        uniqueController.setOnIsUnique(onIsUnique);
        //show window
        stage.show();
    }

    @FXML
    public void clickedRemoveItem(ActionEvent actionEvent) {
        // call remove item from Inventory Central
        //use the table index as a parameter
        inv.removeItem(getTableIndex());
    }

    @FXML
    public void clickedSaveTSVInventory(ActionEvent actionEvent) throws IOException {
        //set window name
        fileChooser.setTitle("Save File");
        //set file type
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        //create file
        File savedInventory = fileChooser.showSaveDialog(new Stage());
        if (savedInventory != null){
            file.saveTSVInventory(inv.getWholeList(), savedInventory);
        }
    }

    @FXML
    public void clickedSaveHTMLInventory(ActionEvent actionEvent) throws IOException {
        //set window name
        fileChooser.setTitle("Save File");
        //set file type
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML file", "*.html"));
        //create file
        File savedInventory = fileChooser.showSaveDialog(new Stage());
        if (savedInventory != null){
            file.saveHTMLInventory(inv.getWholeList(), savedInventory);
        }
    }

    @FXML
    public void clickedLoadTSVInventory(ActionEvent actionEvent) throws IOException {
        //set window name
        fileChooser.setTitle("Load File");
        //set file type
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        //load file
        File chosenList = fileChooser.showOpenDialog(new Stage());
        tableView.setItems(inv.setList(file.loadTSVInventory(chosenList)));
    }

    @FXML
    public void clickedLoadHTMLInventory(ActionEvent actionEvent) throws IOException {
        //set window name
        fileChooser.setTitle("Load File");
        //set file type
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML file", "*.html"));
        //load file
        File chosenList = fileChooser.showOpenDialog(new Stage());
        tableView.setItems(inv.setList(file.loadHTMLInventory(chosenList)));
    }

    @FXML
    public void clickedEditItem(ActionEvent actionEvent) throws IOException {
        //open New Item Window
        FXMLLoader root = new  FXMLLoader(getClass().getResource("EditItemWindow.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Edit Item");
        stage.setScene(new Scene(root.load()));
        //set up edit controller
        EditItemWindowController editController = root.getController();
        editController.setOnItemEdited(onItemEdited);
        //set up unique controller
        EditItemWindowController uniqueController = root.getController();
        uniqueController.setOnIsUnique(onIsUnique);
        //show window
        stage.show();
    }
}
