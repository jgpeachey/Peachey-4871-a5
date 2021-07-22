package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryCentral {

    //create a list of items seen
    private ObservableList<Item> displayList= FXCollections.observableArrayList();
    //create a main list of items
    private ObservableList<Item> list= FXCollections.observableArrayList();
    //create a index conversion

    public int indexConverter(int index){
        int i = 0;
        //convert index
        while (!displayList.get(index).equals(list.get(i))){
            i++;
        }
        return i;
    }

    //get the displayed list
    public ObservableList<Item> getDisplayList(){
        //return list
        return displayList;
    }

    //get the whole list
    public ObservableList<Item> getWholeList(){
        //return list
        return list;
    }
}
