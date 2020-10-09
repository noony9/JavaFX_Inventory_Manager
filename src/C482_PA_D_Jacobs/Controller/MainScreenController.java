package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.*;
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

/**
 * This class is the controller to be used to control the flow of data into the Main Screen, updates the view
 * as required and functions as the home screen for the application.
 * */
public class MainScreenController implements Initializable {
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TextField partsSearchBox;
    @FXML
    private TextField productsSearchBox;

    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     * @param url Uniformed Resource Locator
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize Parts
        partsTableView.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // set focus on first part in the table
        partsTableView.getSelectionModel().selectFirst();
        partsTableView.getFocusModel().focus(0);

        // Initialize Products
        productTableView.setItems(Inventory.getAllProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // set focus on first product in the table
        productTableView.getSelectionModel().selectFirst();
        productTableView.getFocusModel().focus(0);

    }

    /** Handles the exit event.
     * This method exits the application.
     * @param actionEvent The event
     */
    public void onActionExitButton(ActionEvent actionEvent) {

        System.exit(0);
    }

    /** Handles the Add Part event.
     * This method switches screens from Main Screen to Add Part Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void onActionAddPart(ActionEvent actionEvent) throws IOException{

        // navigate to Add Part Screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Modify Part event.
     * This method instantiates an FXMLLoader to load the resource and a controller to transfer the selected Part to
     * the Modify Part Screen, then switches screens from Main Screen to Modify Part Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * @see ModifyPartScreenController#sendPart(Part)
     */
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {

        // load Modify Part Screen hierarchy from it's source
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/ModifyPartScreen.fxml"));
        loader.load();

        // instantiate Modify Part Screen to enable transfer of selected part
        ModifyPartScreenController modifyPartScreenController = loader.getController();

        // transfer selected part
        modifyPartScreenController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

        // navigate to Modify Part Screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Delete Part event.
     * This method alerts the user to confirm their intention to delete, then attempts to delete the part if it exists.
     * @param actionEvent The event
     * @throws NullPointerException Thrown when an application attempts to use null in a case where an object is required.
     */
    public void onActionDeletePart(ActionEvent actionEvent) throws NullPointerException {

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

    /** Handles the Add Product event.
     * This method switches screens from Main Screen to Add Product Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {

        // navigate to Add Product Screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Modify Part event.
     * This method instantiates an FXMLLoader to load the resource and a controller to transfer the selected Product to
     * the Modify Product Screen, then switches screens from Main Screen to Modify Part Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {

        // load Modify Product Screen hierarchy from it's source
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/ModifyProductScreen.fxml"));
        loader.load();

        // instantiate Modify Product Screen to enable transfer of selected part
        ModifyProductScreenController modifyProductScreenController = loader.getController();

        // transfer selected product
        modifyProductScreenController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

        // navigate to Modify Product Screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Delete Product event.
     * This method alerts the user to confirm their intention to delete, then attempts to delete the product if it exists.
     * @param actionEvent The event
     * @throws NullPointerException Thrown when an application attempts to use null in a case where an object is required.
     */
    public void onActionDeleteProduct(ActionEvent actionEvent) {

        // alert user to confirm delete
        Alert confirmDeleteProduct = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteProduct.setTitle("Please Confirm Delete");
        Optional<ButtonType> input = confirmDeleteProduct.showAndWait();

        // execute deletion if user confirms
        if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
            try {
                Product product = productTableView.getSelectionModel().getSelectedItem();

                // ensure that selected product does not contain associated parts
                if (productTableView.getSelectionModel().getSelectedItem().getAssociatedParts().size() > 0){

                    // alert user that the product cannot be deleted as it has associated parts
                    Alert unableToDelete = new Alert(Alert.AlertType.ERROR);
                    unableToDelete.setTitle("Error");
                    unableToDelete.setContentText("You cannot delete at Product that contains associated parts!");
                    Optional<ButtonType> userInput = unableToDelete.showAndWait();

                }
                else {
                    Inventory.deleteProduct(product);
                }
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

    /** This method resets the Filtered Parts List and adds the parts that match the user's input string.
     * This method clears the Filtered Parts List, loops through the Filtered Parts List to identify if the
     * part ID or Name contains user input, then adds the part to the Filtered Parts List
     * and returns the Filtered Parts List.
     * @param string The string to be filtered
     * @return allFilteredParts
     * @see MainScreenController#onActionPartSearch(ActionEvent)
     */
    public ObservableList<Part> filterPart(String string) {

        if(!(Inventory.getAllFilteredParts().isEmpty())){
            Inventory.getAllFilteredParts().clear();
        }

        return Inventory.lookupPart(string);
    }

    /** This method resets the Filtered Products List and adds the products that match the user's input string.
     * This method clears the Filtered Products List, loops through the Filtered Products List to identify if the
     * product ID or Name contains user input, then adds the part to the Filtered Products List
     * and returns the Filtered Products List.
     * @param string The string to be filtered
     * @return allFilteredProducts
     * @see MainScreenController#onActionProductSearch(ActionEvent)
     */
    public ObservableList<Product> filterProduct(String string) {

        if(!(Inventory.getAllFilteredProducts().isEmpty())){
            Inventory.getAllFilteredProducts().clear();
        }
        return Inventory.lookupProduct(string);
    }

    /** This method filters the Parts TableView. 
     * This method captures user input in the search box, calls filterPart() method to filter the Parts TableView,
     * then sets the Parts TableView with the filtered results.
     * @param actionEvent The event
     */
    public void onActionPartSearch(ActionEvent actionEvent) {

        // get text from search text box and store in a string
        String searchString = partsSearchBox.getText();

        // filter parts based on the search string
        filterPart(searchString);

        // initialize parts
        partsTableView.setItems(Inventory.getAllFilteredParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method filters the Products TableView. 
     * This method captures user input in the search box, calls filterProduct() method to filter the Product TableView,
     * then sets the Product TableView with the filtered results.
     * @param actionEvent The event
     */
    public void onActionProductSearch(ActionEvent actionEvent) {

        // get text from search text box and store in a string
        String searchString = productsSearchBox.getText();

        // filter parts based on the search string
        filterProduct(searchString);

        // initialize products
        productTableView.setItems(Inventory.getAllFilteredProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
