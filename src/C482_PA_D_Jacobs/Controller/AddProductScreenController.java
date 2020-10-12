package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.Inventory;
import C482_PA_D_Jacobs.Model.Part;
import C482_PA_D_Jacobs.Model.Product;
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

/**
 * This class is the controller to be used to control the flow of data into the Add Product Screen and updates the view
 * as required.
 * */
public class AddProductScreenController implements Initializable {

    @FXML
    private TextField productInvText;
    @FXML
    private TextField productIDText;
    @FXML
    private TextField productMinText;
    @FXML
    private TextField productPriceText;
    @FXML
    private TextField productNameText;
    @FXML
    private TextField productMaxText;
    @FXML
    private TextField productSearchBox;
    @FXML
    private Button productPartsListAddButton;
    @FXML
    private Button productCancelButton;
    @FXML
    private Button productSaveButton;
    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private TableView<Part> productPartsListTableView;
    @FXML
    private TableColumn<Part, Integer> productPartIDCol;
    @FXML
    private TableColumn<Part, String> productPartNameCol;
    @FXML
    private TableColumn<Part, Integer> productPartInvCol;
    @FXML
    private TableColumn<Part, Double> productPartPriceCol;

    @FXML
    private TableView<Part> productAssociatedPartsTableView;
    @FXML
    private TableColumn<Part, Integer> productAssociatedPartIDCol;
    @FXML
    private TableColumn<Part, String> productAssociatedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> productAssociatedPartInvCol;
    @FXML
    private TableColumn<Part, Double> productAssociatedPartPriceCol;

    public ObservableList<Part> partList = FXCollections.observableArrayList();

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
        productPartsListTableView.setItems(Inventory.getAllParts());

        productPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Handles the save event.
     * This method first validates user input in all fields, assigns a unique ID, captures input in the TextFields
     * into local variables, creates a new Product, updates the product with the local variables and associated parts
     * with the partsList global variable, then returns the user to the Main Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * */
    public void onActionSaveAddProduct(ActionEvent actionEvent) throws IOException {

        // Programmed into this code block is an input validator method that is inserted as a gatekeeper to prevent
        // common runtime IO exceptions from user input and controlling the user's ability to save data entered into
        // form fields until user input meets specifications defined in the validator method and assists the user in
        // identifying appropriate input by using pop-up warnings to guide input.  The onActionSaveAddProduct method
        // will throw an IOException to catch any exceptional cases of user input not prevented by the input validator
        // method.
        if (validateInput()){
            try {

                int maxID = 0;

                // loop through inventory parts list to identify the largest part ID
                for (Product p : Inventory.getAllProducts()){

                    // capture index ID
                    int indexID = p.getId();

                    // update the max ID variable if index ID is greater
                    if (indexID > maxID){
                        maxID = indexID;
                    }
                }

                // set id equal to largest part ID + 1 to establish a unique ID
                int id = maxID +1;

                // capture field data and save
                String name = productNameText.getText();
                double price = Double.parseDouble(productPriceText.getText());
                int stock = Integer.parseInt(productInvText.getText());
                int min = Integer.parseInt(productMinText.getText());
                int max = Integer.parseInt(productMaxText.getText());

                // create the product
                Product product = new Product(id, name, price, stock, min, max);

                // add the product's associated parts
                product.getAssociatedParts().addAll(partList);

                // update the product
                Inventory.addProduct(product);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // return to main screen
            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } // end if
    } // end method

    /** Handles the cancel event.
     * This method returns the user to the Main Screen without saving any of the data modifications.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * */
    public void onActionCancelAddProduct(ActionEvent actionEvent) throws IOException {

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Add Part from Parts TableView to Associated Parts TableView event.
     * This method instantiates a part from user selected part and adds the part to the global partList, then sets the
     * Associated Parts TableView with the global partList.
     * @param actionEvent The event
     */
    public void onActionPartsListAddButton(ActionEvent actionEvent) {

        Part partToBeAdded = productPartsListTableView.getSelectionModel().getSelectedItem();
        partList.add(partToBeAdded);

        // Initialize Associated Parts
        productAssociatedPartsTableView.setItems(partList);

        productAssociatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Handles the Remove Part from Associated Parts TableView
     * This method instantiates a part from user selected part, validates that the global partList is not empty,
     * confirms the user's decision to remove, then removes the part from the global partList and Associated Parts
     * TableView.
     * @param actionEvent The event
     * @throws NullPointerException Thrown when an application attempts to use null in a case where an object is required.
     */
    public void onActionRemoveAssociatedPartButton(ActionEvent actionEvent) throws NullPointerException {

        // generate part to be removed from associated parts
        Part partToBeRemoved = productAssociatedPartsTableView.getSelectionModel().getSelectedItem();

        // execute if parts list is not null
        if (partList.size() > 0){

            // alert user to confirm delete
            Alert confirmDeleteProduct = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDeleteProduct.setTitle("Please Confirm Delete");
            Optional<ButtonType> input = confirmDeleteProduct.showAndWait();

            // execute part removal from associated parts list if user selects OK
            if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
                try {
                    partList.remove(partToBeRemoved);
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
    }

    /** This method resets the Filtered Parts List and adds the parts that match the user's input string.
     * This method clears the global Filtered Parts List, loops through the Filtered Parts List to identify if the
     * part ID or Name contains user input, then adds the part to the Filtered Parts List
     * and returns the Filtered Parts List.
     * @param string The string to be filtered
     * @return Inventory.getAllFilteredParts()
     * @see MainScreenController#onActionPartSearch(ActionEvent)
     */
    public ObservableList<Part> filterPart(String string) {

        if(!(Inventory.getAllFilteredParts().isEmpty())){
            Inventory.getAllFilteredParts().clear();
        }

        return Inventory.lookupPart(string);
    }

    /** This method filters the Parts TableView.
     * This method captures user input in the search box, calls filterPart() method to filter the Parts TableView,
     * then sets the Parts TableView with the filtered results.
     * @param actionEvent The event
     */
    public void onActionPartSearchBox(ActionEvent actionEvent) {

        // get text from search text box and store in a string
        String searchString = productSearchBox.getText();

        // filter parts based on the search string
        filterPart(searchString);

        // initialize parts
        productPartsListTableView.setItems(Inventory.getAllFilteredParts());

        productPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the application's de facto input validation method used by the Add and Modify views' save method
     * to ensure valid input is provided prior to proceeding with instancing or updating the products.
     * This method validates in this order: ensure no fields are empty (ID is auto-generated and is left out of all
     * validation); ensure no inappropriate chars are present; ensure min less than max; ensure inventory is greater
     * than or equal to min and less than or equal to max; and ensure that a double (from 1.0 - 999,999) is entered as
     * the price.
     * @return true if input in all fields are valid or false if not.
     * @see AddProductScreenController#onActionSaveAddProduct(ActionEvent)
     * @see ModifyProductScreenController#onActionSaveAddProduct(ActionEvent)
     */
    public boolean validateInput() {

        // validate input present in all text fields
        if ((productNameText.getText().trim().isEmpty() || productPriceText.getText().trim().isEmpty()
                || productInvText.getText().trim().isEmpty() || productMinText.getText().trim().isEmpty() ||
                productMaxText.getText().trim().isEmpty())) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Please enter information into all fields before saving.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate non special chars in all text fields
        String alphaNumericWithSpace = "^[a-zA-Z0-9_ ]+$";
        String numeric = "^[0-9]+$";
        String numericWithDecimal = "^[0-9_.]+$";

        if ((!productNameText.getText().matches(alphaNumericWithSpace) ||
                !productPriceText.getText().matches(numericWithDecimal ) ||
                !productInvText.getText().matches(numeric) ||
                !productMinText.getText().matches(numeric) ||
                !productMaxText.getText().matches(numeric))) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Please do not enter special characters or letters where numbers are expected " +
                    "or vice-versa.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate min less than max
        else if (Integer.parseInt(productMinText.getText()) > Integer.parseInt(productMaxText.getText())) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Min must be less than max.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate inventory is greater than or equal to min and less than or equal to max
        else if (Integer.parseInt(productInvText.getText()) < Integer.parseInt(productMinText.getText()) ||
                Integer.parseInt(productInvText.getText()) > Integer.parseInt(productMaxText.getText())) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Inventory must be greater than or equal to min and less than or equal to max.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate expected double data type (up to 999,999 or 999,999.99)
        else if (!(productPriceText.getText().matches("[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9]|9\\.9") ||
                productPriceText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]|9\\.9"))) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Please enter a value from 0 - 999,999.  You may enter a decimal up to" +
                    " " + "999,999.99.  Please leave commas out of your input value.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        return true;
    }
}