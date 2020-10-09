package C482_PA_D_Jacobs.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is the model that stores data for Parts and Products and contains the methods to update, add, remove,
 * and retrieve them.
 * */

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();
    public static ObservableList<Part> getAllParts(){

        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){

        return allProducts;
    }

    public static ObservableList<Part> getAllFilteredParts(){

        return allFilteredParts;
    }

    public static ObservableList<Product> getAllFilteredProducts(){

        return allFilteredProducts;
    }

    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static void updatePart(int id, Part part){
        for (Part p : allParts){
            if (id == p.getId()){
                p.setName(part.getName());
                p.setName(part.getName());
                p.setPrice(part.getPrice());
                p.setStock(part.getStock());
                p.setMin(part.getMin());
                p.setMax(part.getMax());
                if (part.getClass().isInstance(InhousePart.class)){
                    System.out.println("Is an instance of Inhouse Part");
                }
            }
        }
    }

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

    public static Part lookupPart(int id){
        for (Part p : allParts){
            if (id == p.getId()){
                return p;
            }
        }
        return null;
    }

    public static Product lookupProduct(int id){
        for (Product p : allProducts){
            if (id == p.getId()){
                return p;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String name){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for (Part p : allParts){
            if (name.equals(p.getName())){

                return parts;
            }
        }
       //return null;
        return parts;
    }

    public static ObservableList<Product> lookupProduct(String name){
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product p : allProducts){
            if (name.equals(p.getName())){
                return products;
            }
        }
        //return null;
        return products;
    }

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
