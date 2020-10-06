package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    public TableView<Part> partsTableView;
    public TableColumn<Part, Integer> partIDCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partInventoryCol;
    public TableColumn<Part, Double> partPriceCol;

    public TableView<Product> productTableView;
    public TableColumn<Product, Integer> productIDCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productInventoryCol;
    public TableColumn<Product, Double> productPriceCol;
    public TextField partsSearchBox;
    public TextField productsSearchBox;

    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize Parts
        partsTableView.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Initialize Products
        productTableView.setItems(Inventory.getAllProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


//    public Part selectPart(int id) {
//
//        for (Part part : Inventory.getAllParts()) {
//            if (part.getId() == id) {
//                return part;
//            }
//        }
//        return null;
//    }

    public void onActionExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/ModifyPartScreen.fxml"));
        loader.load();

        ModifyPartScreenController modifyPartScreenController = loader.getController();
        modifyPartScreenController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

        if (partsTableView.getSelectionModel().getSelectedItem() instanceof OutsourcedPart) {

            // if outsourced, change default label "Machine ID" to "Company Name"
            modifyPartScreenController.machineIDLabel.setText("Company Name");

            // change radio button from default position "Inhouse" to "Outsourced"
            modifyPartScreenController.outsourcedRadioButton.setSelected(true);

        }

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeletePart(ActionEvent actionEvent) {
        // alert user to confirm delete
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Please Confirm Delete");
        Optional<ButtonType> input = confirmation.showAndWait();

        // execute deletion if user confirms
        if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
            try {
                Part part = partsTableView.getSelectionModel().getSelectedItem();
                Inventory.deletePart(part);
            }
            catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Part not removed");
                alert.setContentText("No part selected for removal");
                alert.show();
            }
        }
    }

    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/ModifyProductScreen.fxml"));
        loader.load();

        ModifyProductScreenController modifyProductScreenController = loader.getController();
        modifyProductScreenController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteProduct(ActionEvent actionEvent) {

        // alert user to confirm delete
        Alert confirmDeleteProduct = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteProduct.setTitle("Please Confirm Delete");
        Optional<ButtonType> input = confirmDeleteProduct.showAndWait();

        // execute deletion if user confirms
        if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
            try {
                Product product = productTableView.getSelectionModel().getSelectedItem();
                Inventory.deleteProduct(product);
            }
            catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Product not removed");
                alert.setContentText("No product selected for removal");
                alert.show();
            }
        }
    }

    public ObservableList<Part> filterPart(String string) {

        if(!(Inventory.getAllFilteredParts().isEmpty())){
            Inventory.getAllFilteredParts().clear();
        }

        for (Part part : Inventory.getAllParts()) {
            if (part.getName().contains(string) || String.valueOf(part.getId()).equals(string)){
                Inventory.getAllFilteredParts().add(part);
            }
        }

        return Inventory.getAllFilteredParts();
    }

    public ObservableList<Product> filterProduct(String string) {

        if(!(Inventory.getAllFilteredProducts().isEmpty())){
            Inventory.getAllFilteredProducts().clear();
        }

        for (Product product : Inventory.getAllProducts()) {
            if (product.getName().contains(string) || String.valueOf(product.getId()).equals(string)){
                Inventory.getAllFilteredProducts().add(product);
            }
        }
        return Inventory.getAllFilteredProducts();
    }


    public void onActionPartSearch(ActionEvent actionEvent) {

        // get text from search text box and store in a string
        String searchString = partsSearchBox.getText();

        // filter parts based on the search string
        filterPart(searchString);

        // Initialize Parts
        partsTableView.setItems(Inventory.getAllFilteredParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void onActionProductSearch(ActionEvent actionEvent) {

        //public TextField productsSearchBox;
        // get text from search text box and store in a string
        String searchString = productsSearchBox.getText();

        // filter parts based on the search string
        filterProduct(searchString);

        // Initialize Parts
        productTableView.setItems(Inventory.getAllFilteredProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
