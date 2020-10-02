package C482_PA_D_Jacobs.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product {
    //FIXME observable associated parts list
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getMin() {

        return min;
    }

    public void setMin(int min) {

        this.min = min;
    }

    public int getMax() {

        return max;
    }

    public void setMax(int max) {

        this.max = max;
    }

    public void addAssociatedPart(Part part){

        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(int partID){
        boolean check = false;
        for (Part part : associatedParts){
            if (part.getId() == partID){
                associatedParts.remove(part);
                return check = true;
            }
            else check = false;
        }
        return check; //verify
    }
}