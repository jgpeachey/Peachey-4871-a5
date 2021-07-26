package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileCentralTest {

    @Test
    void saveTSVInventory() throws IOException {
        // given
        FileCentral fileCentral = new FileCentral();
        //create observable list to save
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W"),
                new Item("item2", new BigDecimal("20.0"), "B4E8R5FF5W")
        );
        //create file location to save to
        File file = new File("src/test/resources/TestOutputFile.txt");

        //when
        //cal save list from list central
        fileCentral.saveTSVInventory(expected, file);

        //then
        //make sure file has data in it
        assertNotNull(file);
    }

    @Test
    void saveHTMLInventory() {
        // given
        FileCentral fileCentral = new FileCentral();
        //create observable list to save
        ObservableList<Item> expected = FXCollections.observableArrayList(
                new Item("item", new BigDecimal("10.0"), "A4E8R5FF5W"),
                new Item("item2", new BigDecimal("20.0"), "B4E8R5FF5W")
        );
        //create file location to save to
        File file = new File("src/test/resources/TestOutputFile.txt");

        //when
        //cal save list from list central
        fileCentral.saveHTMLInventory(expected, file);

        //then
        //make sure file has data in it
        assertNotNull(file);
    }

    @Test
    void loadTSVInventory() throws IOException {
        // given
        FileCentral fileCentral = new FileCentral();
        //create file location to load from
        File file = new File("src/test/resources/TestInputFile.txt");

        //when
        //create observable list
        ObservableList<Item> actual = FXCollections.observableArrayList();
        //save file data to observable list
        actual.addAll(fileCentral.loadTSVInventory(file));

        //then
        //make sure data is in observable list
        assertNotNull(actual);
    }

    @Test
    void loadHTMLInventory() throws IOException {
        // given
        FileCentral fileCentral = new FileCentral();
        //create file location to load from
        File file = new File("src/test/resources/TestInputFile.txt");

        //when
        //create observable list
        ObservableList<Item> actual = FXCollections.observableArrayList();
        //save file data to observable list
        actual.addAll(fileCentral.loadHTMLInventory(file));

        //then
        //make sure data is in observable list
        assertNotNull(actual);
    }
}