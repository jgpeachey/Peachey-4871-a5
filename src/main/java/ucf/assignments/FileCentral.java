/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 John Peachey
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCentral {

    InventoryCentral inv = new InventoryCentral();

    public void saveTSVInventory(ObservableList<Item> inventory, File outputFile) throws IOException {
        //implement a try catch method
        try {
            //set up file writer
            FileWriter fileWriter = new FileWriter(outputFile);
            PrintWriter writer = new PrintWriter(fileWriter);
            //write appropriate statements to file
            writer.printf("%1$20s\t\t%2$3s\t\t%3$3s\n", "Value($)", "Serial Number", "Name");
            //go through array to print output to file
            for (Item item : inventory) {
                // print entire item info in one loop
                String name = item.getName();
                String serialNumber = item.getSerialNumber();
                BigDecimal value = new BigDecimal(item.getValue().toString());
                //writer.print(inventory.get(i).getValue().toString()+"\t");
                writer.printf("%1$20s\t\t%2$3s\t\t%3$3s\n", value, serialNumber, name);
            }
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("Failed to write file.");
            e.printStackTrace();
        }
        //return file
    }

    public void saveHTMLInventory(ObservableList<Item> inventory, File outputFile){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            bw.write("<html>\n<body>\n<h1>Inventory</h1>\n");
            bw.write("<table>\n<tr>\n<th>Value($)</th><th>Serial Number</th><th>Name</th>\n</tr>\n");
            for (Item item : inventory) {
                BigDecimal value = new BigDecimal(item.getValue().toString());
                bw.write("<tr>\n<td>"+value+"</td>\n<td>"+item.getSerialNumber()+"</td>\n<td>"+item.getName()+"</td>\n</tr>\n");
            }
            bw.write("</table>\n</body>\n</html>");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Item> loadTSVInventory(File inputFile) throws IOException {
        //create observable list
        ObservableList<Item> inventory = FXCollections.observableArrayList();

        StringTokenizer in ;
        BufferedReader TSVFile = new BufferedReader(new FileReader(inputFile));
        String itemLine = TSVFile.readLine(); // Read first line.

        while (itemLine != null){
            in = new StringTokenizer(itemLine,"\t");
            while(in.hasMoreElements()){
                BigDecimal value = new BigDecimal("10.0");
                String serialNumber = in.nextElement().toString();
                String name = in.nextElement().toString();
                inventory.add(new Item(name, value, serialNumber));
            }
            itemLine = TSVFile.readLine(); // Read next line of data.
        }
        // Close the file once all data has been read.
        TSVFile.close();
//        //load file
//        try {
//            Scanner in = new Scanner(inputFile);
//            //skips table headers
//            in.next(); in.next(); in.next();
//            in.useDelimiter("\t");
//            in.skip(" ");
//            // reads next input from file
//            while (in.hasNextLine()) {
//                //get data for an item
//                BigDecimal value = new BigDecimal(in.next());
//                String serialNumber = in.next();
//                String name = in.next();
//                //set file data to list
//                inventory.add(new Item(name, value, serialNumber));
//            }
//        }
//        //catch method to make sure file is found
//        catch (FileNotFoundException e){
//            System.out.println("File not found.");
//            e.printStackTrace();
//        }
        //set list in list central
        inv.setList(inventory);
        //return
        return inventory;
    }

    public ObservableList<Item> loadHTMLInventory(File inputFile) throws IOException {
        //create observable list
        ObservableList<Item> inventory = FXCollections.observableArrayList();
        //create temp array to store data
        ArrayList<String> temp = new ArrayList<>();

        //create readers and patterns
        BufferedReader HTMLFile = new BufferedReader(new FileReader(inputFile));
        String regexString = Pattern.quote("<td>")+"(.*?)"+Pattern.quote("</td>");
        Pattern p = Pattern.compile(regexString);

        //take in first line
        String itemLine = HTMLFile.readLine();

        //loop through entire document
        while (itemLine != null) {
            //set matcher to current line
            Matcher m = p.matcher(itemLine);
            //find pattern and take in data between
            while (m.find()){
                temp.add(m.group(1));
            }
            //take in next line
            itemLine = HTMLFile.readLine();
        }

        //set up item
        String value = null;
        String serialNumber = null;
        String name = null;

        //loop through temp and create items
        for (int i = 0; i < temp.size(); i+=3) {
            value = temp.get(i);
            serialNumber = temp.get(i+1);
            name = temp.get(i+2);
            //adds items
            inventory.add(new Item(name, new BigDecimal(value), serialNumber));
        }

        return inventory;
    }

}
