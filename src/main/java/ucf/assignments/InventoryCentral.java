package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class InventoryCentral {

    //create a list of items seen
//    private ObservableList<Item> displayList= FXCollections.observableArrayList();
    //create a main list of items
    //private ObservableList<Item> entireList = FXCollections.observableArrayList();

    Inventory inventory = new Inventory();

    //create a index conversion
    public int indexConverter(int index){
        int i = 0;
        if (index != 0) {
            //convert index
            while (!inventory.getDisplayList().get(index).equals(inventory.getEntireList().get(i))) {
                i++;
            }
        }
        return i;
    }

    //get the displayed list
    public ObservableList<Item> getDisplayList(){
        //return list
        return inventory.getDisplayList();
    }

    //get the whole list
    public ObservableList<Item> getWholeList(){
        //return list
        return inventory.getEntireList();
    }

    public ObservableList<Item> setList(ObservableList<Item> newList){
        //clear any existing list
        removeAllItems();
        //add loaded list
        inventory.setEntireList(newList);
        inventory.setDisplayList(newList);
        //return list
        return inventory.getEntireList();
    }

    public Item addItem(Item item){
        //add new item to list
        inventory.getEntireList().add(item);
        inventory.getDisplayList().add(item);
        return item;
    }

    public Item editItemInfo(int index, String name, BigDecimal value, String serialNumber){
        inventory.getDisplayList().get(index).setName(name);
        inventory.getDisplayList().get(index).setValue(value);
        inventory.getDisplayList().get(index).setSerialNumber(serialNumber);
        inventory.getEntireList().get(indexConverter(index)).setName(name);
        inventory.getEntireList().get(indexConverter(index)).setValue(value);
        inventory.getEntireList().get(indexConverter(index)).setSerialNumber(serialNumber);
        return inventory.getEntireList().get(indexConverter(index));
    }

    public ObservableList<Item> removeItem(int index){
        // remove item from display list
        inventory.getDisplayList().remove(index);
        // remove item from list
        inventory.getEntireList().remove(indexConverter(index));
        return inventory.getDisplayList();
    }

    public ObservableList<Item> removeItem(Item item){
        // remove item from display list
        inventory.getDisplayList().remove(item);
        // remove item from list
        inventory.getEntireList().remove(item);
        return inventory.getDisplayList();
    }

    public ObservableList<Item> removeAllItems(){
        //remove everything from every list
        inventory.getDisplayList().remove(0, inventory.getDisplayList().size());
        inventory.getEntireList().remove(0, inventory.getEntireList().size());
        return inventory.getEntireList();
    }

    public boolean helpEmOut(URI uri){
        if (Desktop.isDesktopSupported()){
            try{
                Desktop.getDesktop().browse(uri);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return Desktop.isDesktopSupported();
    }
}
