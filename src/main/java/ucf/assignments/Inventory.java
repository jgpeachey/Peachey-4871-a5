/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 John Peachey
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    //create a list of items seen
    private ObservableList<Item> displayList= FXCollections.observableArrayList();
    //create a main list of items
    private ObservableList<Item> entireList = FXCollections.observableArrayList();


    public ObservableList<Item> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(ObservableList<Item> displayList) {
        this.displayList.setAll(displayList);
    }

    public ObservableList<Item> getEntireList() {
        return entireList;
    }

    public void setEntireList(ObservableList<Item> entireList) {
        this.entireList.setAll(entireList);
    }
}
