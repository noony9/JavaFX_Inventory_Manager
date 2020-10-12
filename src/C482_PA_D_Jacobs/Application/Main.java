package C482_PA_D_Jacobs.Application;

import C482_PA_D_Jacobs.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class creates an application that represents an Inventory Management Application.
 * */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }

 /** This is the main method. This is the first method that gets called when the program is executed.
  * This method will instantiate dummy parts and products into memory for the first version of the application.  In the
  * future, the application will be extended to connect to a database for data persistence.
  * @param args Arguments
  */
    public static void main(String[] args) {

        // Create dummy parts
        Part robotArm = new InhousePart(0,"Robot Arms", 10.00, 150, 1, 500,111);
        Part robotLeg = new InhousePart(1,"Robot Legs", 15.00, 100, 1, 500,112);
        Part robotHead = new InhousePart(2,"Robot Head", 20.00, 50, 1, 500,113);
        Part robotTorso = new OutsourcedPart(3,"Robot Torso", 11.00, 80, 0, 100,
                "XYZ Parts");
        Part robotHands = new OutsourcedPart(4,"Robot Hands", 16.00, 60, 0, 75,
                "XYZ Parts");
        Part robotFeet = new OutsourcedPart(5,"Robot Feet", 21.00, 20, 0, 30,
                "XYZ Parts");
        Part tenWDFourty = new OutsourcedPart(6,"10wd40 Oil", 25.00, 20, 0, 30,
                "XYZ Parts");

        // Add parts to observable list
        Inventory.addPart(robotArm);
        Inventory.addPart(robotLeg);
        Inventory.addPart(robotHead);
        Inventory.addPart(robotTorso);
        Inventory.addPart(robotHands);
        Inventory.addPart(robotFeet);
        Inventory.addPart(tenWDFourty);

        // Create dummy products
        Product genericBot1 = new Product(0,"genericBot1", 40.00, 25, 1, 100);
        Product genericBot2 = new Product(1,"genericBot2", 50.00, 27, 1, 100);
        Product genericBot3 = new Product(2,"genericBot3", 60.00, 22, 1, 100);
        Product genericBot4 = new Product(3,"genericBot4", 70.00, 10, 1, 100);
        Product genericBot5 = new Product(4,"genericBot5", 80.00, 14, 1, 100);

        // Add products to the observable list
        Inventory.addProduct(genericBot1);
        Inventory.addProduct(genericBot2);
        Inventory.addProduct(genericBot3);
        Inventory.addProduct(genericBot4);
        Inventory.addProduct(genericBot5);

        // Associate parts with products
        genericBot1.addAssociatedPart(robotArm);
        genericBot2.addAssociatedPart(robotLeg);
        genericBot3.addAssociatedPart(robotArm);
        genericBot4.addAssociatedPart(tenWDFourty);
        genericBot4.addAssociatedPart(robotTorso);
        genericBot5.addAssociatedPart(robotHands);
        genericBot5.addAssociatedPart(robotFeet);

        launch(args);
    }
}
