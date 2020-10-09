package C482_PA_D_Jacobs.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * This class is the model that stores data for Parts and Products and contains the methods to update, add, remove,
 * and retrieve them.
 * */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();

    /**
     * This method returns all parts in allParts list.
     * @return ObservableList allParts
     * */
    public static ObservableList<Part> getAllParts(){

        return allParts;
    }

    /**
     * This method returns all products in allProducts list.
     * @return ObservableList allProducts
     * */
    public static ObservableList<Product> getAllProducts(){

        return allProducts;
    }

    /**
     * This method returns all parts in allFilteredParts list.
     * @return ObservableList allFilteredParts
     * */
    public static ObservableList<Part> getAllFilteredParts(){

        return allFilteredParts;
    }

    /**
     * This method returns all products in allFilteredProducts list.
     * @return ObservableList allFilteredProducts
     * */
    public static ObservableList<Product> getAllFilteredProducts(){

        return allFilteredProducts;
    }

    /** This method adds the argument to allParts list.
     * @param part part to be added
     * */
    public static void addPart(Part part){

        allParts.add(part);
    }

    /** This method adds the argument to allProducts list.
     * @param product product to be added
     * */
    public static void addProduct(Product product){

        allProducts.add(product);
    }

    /** This method loops through allParts list and updates the part matching the argument, with the
     * argument's properties.
     * @param id ID
     * @param part part to be updated
     * */
    public static void updatePart(int id, Part part){
        for (Part p : allParts){
            if (id == p.getId()){
                p.setName(part.getName());
                p.setName(part.getName());
                p.setPrice(part.getPrice());
                p.setStock(part.getStock());
                p.setMin(part.getMin());
                p.setMax(part.getMax());
            }
        }
    }

    /** This method loops through allProducts list and updates the product matching the argument, with the
     * argument's properties.
     * @param id ID
     * @param product product to be updated
     * */
    public static void updateProduct(int id, Product product){

        for (Product p : allProducts){
            if (id == p.getId()){
                p.setName(product.getName());
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setStock(product.getStock());
                p.setMin(product.getMin());
                p.setMax(product.getMax());
            }
        }
    }

    /** This method loops through allParts list and returns the part with ID that matches argument's ID.
     * This method is not used in the current version of the application, yet is reserved for use in the future.
     * @param id ID
     * @return Part part
     * */
    public static Part lookupPart(int id){
        for (Part p : allParts){
            if (id == p.getId()){
                return p;
            }
        }
        return null;
    }

    /** This method loops through allProducts list and returns the product with ID that matches argument's ID.
     * This method is not used in the current version of the application, yet is reserved for use in the future..
     * @param id ID
     * @return Product product
     * */
    public static Product lookupProduct(int id){
        for (Product p : allProducts){
            if (id == p.getId()){
                return p;
            }
        }
        return null;
    }

    /**
     * This method loops through all parts in allParts list and compares argument to identify a match
     * with any character in the part ID and Name, returns all matching parts in allFilteredParts list, else
     * alerts user that part was not found.
     * @param searchString user entered string
     * @return ObservableList allFilteredParts
     * */
    public static ObservableList<Part> lookupPart(String searchString){

        int partsFound = 0;
        for (Part p : allParts){
            if (p.getName().toLowerCase().contains(searchString.toLowerCase()) ||
                    String.valueOf(p.getId()).equals(searchString)){
                allFilteredParts.add(p);
                ++partsFound;
            }
        }
        if (partsFound == 0){

            // alert user that part was not found and return null
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Part Not Found");
            alert.setContentText("Please try your search again.");
            alert.showAndWait();
        }
        return getAllFilteredParts();
    }

    /**
     * This method loops through all products in allProducts list and compares argument to identify a
     * match with any character in the product ID and Name, returns all matching products in allFilteredProducts list,
     * else alerts user that product was not found.
     * @param searchString user entered string
     * @return ObservableList allFilteredProducts
     * */
    public static ObservableList<Product> lookupProduct(String searchString){

        int productsFound = 0;
        for (Product p : allProducts){
            if (p.getName().toLowerCase().contains(searchString.toLowerCase()) ||
                    String.valueOf(p.getId()).equals(searchString)){

                allFilteredProducts.add(p);
                ++productsFound;
            }
        }
        if (productsFound == 0){

            // alert user that part was not found and return null
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Product Not Found");
            alert.setContentText("Please try your search again.");
            alert.showAndWait();
        }
        return getAllFilteredProducts();
    }

    /** This method removes the part from allParts list if the part ID matches the argument ID.
     * @param part part to be added
     * @return boolean check
     * */
    public static boolean deletePart(Part part){
        boolean check = false;
        for (int i = 0; i < allParts.size(); i++){
            int partID = allParts.get( i ).getId();
            if (part.getId() == partID){
                allParts.remove(part);
                check = true;
            }
            else {
                check = false;
            }
        }
        return check;
    }

    /** This method removes the product from allProducts list if the product ID matches the argument ID.
     * @param product product to be added
     * @return boolean check
     * */
    public static boolean deleteProduct(Product product){
        boolean check = false;
        for (int i = 0; i < allProducts.size(); i++){
            int productID = allProducts.get( i ).getId();
            if (product.getId() == productID){
                allProducts.remove(product);
                check = true;
            }
            else {
                check = false;
            }
        }
        return check;
    }


}
