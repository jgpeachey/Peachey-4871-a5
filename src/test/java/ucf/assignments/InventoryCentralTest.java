package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InventoryCentralTest {

    @Test
    void indexConverter_is_not_null() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create list with an item
        ObservableList<Item> test = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W")
        );
        //set list to display list and list
        inv.setList(test);

        //when
        //get index from list central by calling indexConverter
        int actual = inv.indexConverter(0);

        //then
        //make sure value isn't null
        assertNotNull(actual);
    }

    @Test
    void setList_makes_list_equal_to_list_given() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create list with one item
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W")
        );
        //create empty list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //populate list by calling setList from list central
        actual.setAll(inv.setList(expected));

        //then
        //make sure actual list has changed to hold the expected list data
        assertEquals(expected, actual);
    }

    @Test
    void addItem_adds_item_to_list() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create example item
        Item item = new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W");
        //create expected list with the item in it
        ObservableList<Item> expected = FXCollections.observableArrayList(
                item
        );
        //create empty actual list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //call addItem from list central
        actual.add(inv.addItem(item));

        //then
        //make sure the item was added
        assertEquals(expected, actual);
    }

    @Test
    void removeItem_actually_removes_item() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create test list
        ObservableList<Item> test = FXCollections.observableArrayList();
        Item item1 = new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W");
        Item item2 = new Item("item2", new BigDecimal("10.0"), "A4E8R5FF5W");
        test.add(0, item1);
        test.add(0, item2);
        //set list to display list and list
        inv.setList(test);
        //create expected
        ObservableList<Item> expected = FXCollections.observableArrayList(item2);
        //create empty actual list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //call remove item from list central
        actual.setAll(inv.removeItem(item1));

        //then
        assertEquals(expected, actual);
    }

    @Test
    void removeAllItems_actually_makes_the_list_empty() {
        // given
        InventoryCentral inv = new InventoryCentral();
        ObservableList<Item> test = FXCollections.observableArrayList(
            new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W"),
            new Item("item2", new BigDecimal("20.0"), "B4E8R5FF5W")
        );
        //created empty expected list
        ObservableList<Item> expected = FXCollections.observableArrayList();

        //when
        //set list to displayList and list
        inv.setList(test);
        //call remove all items from list central
        inv.removeAllItems();

        //then
        assertEquals(expected, inv.getDisplayList());
    }

    @Test
    void editItemInfo_edits_name() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create test list with the item in it
        ObservableList<Item> test = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W")
        );
        //create expected list with the item in it
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("toast", new BigDecimal("39999.99"), "SERIALNUMB")
        );
        //create empty actual list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //set test to displayList and list
        inv.setList(test);
        //call editItemInfo from list central
        actual.add(inv.editItemInfo(0, "toast", new BigDecimal("39999.99"), "SERIALNUMB"));

        //then
        //make sure the item was added
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
    }

    @Test
    void editItemInfo_edits_Value() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create test list with the item in it
        ObservableList<Item> test = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W")
        );
        //create expected list with the item in it
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("toast", new BigDecimal("39999.99"), "SERIALNUMB")
        );
        //create empty actual list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //set test to displayList and list
        inv.setList(test);
        //call editItemInfo from list central
        actual.add(inv.editItemInfo(0, "toast", new BigDecimal("39999.99"), "SERIALNUMB"));

        //then
        //make sure the item was added
        assertEquals(expected.get(0).getValue(), actual.get(0).getValue());
    }

    @Test
    void editItemInfo_edits_SerialNumber() {
        // given
        InventoryCentral inv = new InventoryCentral();
        //create test list with the item in it
        ObservableList<Item> test = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W")
        );
        //create expected list with the item in it
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("toast", new BigDecimal("39999.99"), "SERIALNUMB")
        );
        //create empty actual list
        ObservableList<Item> actual = FXCollections.observableArrayList();

        //when
        //set test to displayList and list
        inv.setList(test);
        //call editItemInfo from list central
        actual.add(inv.editItemInfo(0, "toast", new BigDecimal("39999.99"), "SERIALNUMB"));

        //then
        //make sure the item was added
        assertEquals(expected.get(0).getSerialNumber(), actual.get(0).getSerialNumber());
    }

    @Test
    void helpEmOut_Can_Help() throws URISyntaxException {
        // given
        InventoryCentral inv = new InventoryCentral();

        //when
        boolean actual = inv.helpEmOut(new URI("resources/ucf/assignments/help.html"));

        //then
        assertTrue(actual);
    }
}