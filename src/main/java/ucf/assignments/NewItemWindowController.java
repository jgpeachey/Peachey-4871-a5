package ucf.assignments;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NewItemWindowController {

    InventoryCentral inv = new InventoryCentral();

    //button set up
    @FXML
    Button saveButton;
    //text field set ups
    @FXML
    TextField nameTextField;
    @FXML
    TextField serialNumberTextField;
    @FXML
    TextField valueTextField;

    private MainWindowController.OnItemAdded onItemAdded;

    @FXML
    private void clickedSave(Event e) throws IOException {
        if (nameTextField.getText().equals("") || serialNumberTextField.getText().equals("") || valueTextField.getText().equals("")){
            createDialogue("Please enter data for all fields");
        }
        else if (nameTextField.getText().length() > 256){
            createDialogue("Name cannot be longer than 256 characters");
        }
        else if (serialNumberTextField.getText().length() != 10){
            createDialogue("The serial number must be a unique combination of 10 letters/numbers");
        }
        else {
            // make sure onItemAdded isn't empty
            if (onItemAdded != null) {
                //call on item added
                onItemAdded.addItem(new Item(nameTextField.getText(), new BigDecimal(valueTextField.getText()), serialNumberTextField.getText()));
                //go back to main window
                ((Stage) saveButton.getScene().getWindow()).close();
            }
        }
    }

    @FXML
    private void createDialogue(String message){
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Error");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        //show dialogue
        dialog.showAndWait();
    }

    // set the controller for onItemAdded
    public void setOnItemAdded(MainWindowController.OnItemAdded onItemAdded){
        this.onItemAdded = onItemAdded;
    }
}
