package C482_PA_D_Jacobs;

import C482_PA_D_Jacobs.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/MainScreen.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {

        // Create dummy parts
        Part part1 = new InhousePart(0,"part1", 10.00, 150, 1, 500, true,
                111);
        Part part2 = new InhousePart(1,"part2", 15.00, 100, 1, 500, true,
                112);
        Part part3 = new InhousePart(2,"part3", 20.00, 50, 1, 500, true,
                113);
        Part part4 = new OutsourcedPart(3,"part4", 11.00, 80, 0, 100, false,
                "XYZ Parts");
        Part part5 = new OutsourcedPart(4,"part5", 16.00, 60, 0, 75, false,
                "XYZ Parts");
        Part part6 = new OutsourcedPart(5,"part6", 21.00, 20, 0, 30, false,
                "XYZ Parts");

        // Add parts to observable list
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);

        // Create dummy products
        Product product1 = new Product(0,"product1", 40.00, 25, 1, 100);
        Product product2 = new Product(1,"product2", 50.00, 27, 1, 100);
        Product product3 = new Product(2,"product3", 60.00, 22, 1, 100);
        Product product4 = new Product(3,"product4", 70.00, 10, 1, 100);
        Product product5 = new Product(4,"product5", 80.00, 14, 1, 100);

        // Add products to the observable list
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);

        // Associate parts with products
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        product2.addAssociatedPart(part1);
        product3.addAssociatedPart(part3);
        product4.addAssociatedPart(part1);
        product4.addAssociatedPart(part2);
        product4.addAssociatedPart(part4);

        System.out.println(product5.getAssociatedParts());

        launch(args);
    }
}
