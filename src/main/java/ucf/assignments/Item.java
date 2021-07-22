package ucf.assignments;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Item {
    private String name;
    private BigDecimal value;
    private String serialNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Item(String name, BigDecimal value, String serialNumber){
        this.setName(name);
        this.setValue(value);
        this.setSerialNumber(serialNumber);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
