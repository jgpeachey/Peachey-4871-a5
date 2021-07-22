package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class InventoryCentral {

    //create a list of items seen
    private ObservableList<Item> displayList= FXCollections.observableArrayList(
            new Item("Example",  new BigDecimal(10.02), "XA568FE89T")
    );
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

    public Item addItem(Item item){
        //add new item to list
        list.add(item);
        displayList.add(item);
        return item;
    }

    public ObservableList<Item> removeItem(int index){
        // remove item from display list
        displayList.remove(index);
        // remove item from list
        list.remove(indexConverter(index));
        return displayList;
    }
}
