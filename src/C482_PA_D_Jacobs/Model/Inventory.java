package C482_PA_D_Jacobs.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        part.setId(allParts.size());
        allParts.add(part);
    }

    public static void addProduct(Product product){
        product.setId(allProducts.size());
        allProducts.add(product);
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
            if (name == p.getName()){
               // return p;
            }
        }
       //return null;
        return parts;
    }

    public static ObservableList<Product> lookupProduct(String name){
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product p : allProducts){
            if (name == p.getName()){
                // return p;
            }
        }
        //return null;
        return products;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
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

    public static boolean deletePart(Part part){
        boolean check = false;
        for (Part p : allParts){
            if (part.getId() == p.getId()){
                allParts.remove(p);
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
        for (Product p : allProducts){
            if (product.getId() == p.getId()){
                allProducts.remove(p);
                check = true;
            }
            else {
                check = false;
            }
        }
        return check;
    }

}
