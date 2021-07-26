/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 John Peachey
 */

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
    private MainWindowController.OnIsUnique onIsUnique;

    @FXML
    private void clickedSave(Event e) throws IOException {
        if (nameTextField.getText().equals("") || serialNumberTextField.getText().equals("") || valueTextField.getText().equals("")){
            createDialogue("Please enter data for all fields.");
        }
        else if (nameTextField.getText().length() < 2 || nameTextField.getText().length() > 256){
            createDialogue("Name must be between 2 and 256 characters long.");
        }
        else if (serialNumberTextField.getText().length() != 10){
            createDialogue("The serial number must be a unique combination of 10 letters/numbers.");
        }
        else if (!onIsUnique.isUnique(serialNumberTextField.getText())){
            createDialogue("The serial number must be unique.");
        }
        else if (!numFormat(valueTextField.getText())){
            createDialogue("Value must be a number in the format of \"10\" or \"10.00\".");
        }
        else {
            // make sure onItemAdded isn't empty
            if (onItemAdded != null) {
                //call on item added
                onItemAdded.addItem(new Item(nameTextField.getText(), new BigDecimal(valueTextField.getText()), serialNumberTextField.getText().toUpperCase()));
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

    // set the controller for onIsUnique
    public void setOnIsUnique(MainWindowController.OnIsUnique onIsUnique){
        this.onIsUnique = onIsUnique;
    }

    public boolean numFormat(String value){
        boolean isNums = true;
        //go through string character by character
        for (int i = 0; i < value.length(); i++) {
            //determine if char is a num or a .
            if (!Character.isDigit(value.charAt(i)) && value.charAt(i) != '.'){
                    isNums = false;
            }
        }
        return isNums;
    }
}
