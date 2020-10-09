package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.Inventory;
import C482_PA_D_Jacobs.Model.Part;
import C482_PA_D_Jacobs.Model.Product;
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
 * This class is the controller to be used to control the flow of data into the Modify Product Screen and updates the
 * view as required.
 * */
public class ModifyProductScreenController implements Initializable {

    @FXML
    public TextField productInvText;
    @FXML
    public TextField productIDText;
    @FXML
    public TextField productMinText;
    @FXML
    public TextField productPriceText;
    @FXML
    public TextField productNameText;
    @FXML
    public TextField productMaxText;
    @FXML
    public TextField productSearchBox;
    @FXML
    public Button productPartsListAddButton;
    @FXML
    public Button productCancelButton;
    @FXML
    public Button productSaveButton;
    @FXML
    public Button removeAssociatedPartButton;

    @FXML
    public TableView<Part> productPartsListTableView;
    @FXML
    public TableColumn<Part, Integer> productPartIDCol;
    @FXML
    public TableColumn<Part, String> productPartNameCol;
    @FXML
    public TableColumn<Part, Integer> productPartInvCol;
    @FXML
    public TableColumn<Part, Double> productPartPriceCol;

    @FXML
    public TableView<Part> productAssociatedPartsTableView;
    @FXML
    public TableColumn<Part, Integer> productAssociatedPartIDCol;
    @FXML
    public TableColumn<Part, String> productAssociatedPartNameCol;
    @FXML
    public TableColumn<Part, Integer> productAssociatedPartInvCol;
    @FXML
    public TableColumn<Part, Double> productAssociatedPartPriceCol;

    public Product modifiedProduct;
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

    /** This method is used to transfer product data to the Modify Product Screen as well as set the product's
     * Associated Parts TableView.
     * This method first sets the Modify Product Screen TextFields using the product selected for transfer, then
     * initializes the Associated Parts TableView with the product's Associated Parts List.
     * @param product The product to be transferred
     * @see MainScreenController#onActionModifyProduct(ActionEvent)
     */
    public void sendProduct(Product product){

        productIDText.setText(String.valueOf(product.getId()));
        productNameText.setText(product.getName());
        productPriceText.setText(String.valueOf(product.getPrice()));
        productInvText.setText(String.valueOf(product.getStock()));
        productMinText.setText(String.valueOf(product.getMin()));
        productMaxText.setText(String.valueOf(product.getMax()));

        // Initialize Associated Parts TableView
        productAssociatedPartsTableView.setItems(product.getAssociatedParts());

        productAssociatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifiedProduct = product;
    }

    /** Handles the save event.
     * This method first validates user input in all fields, assigns a unique ID, captures input in the TextFields
     * into local variables, creates a new Product, updates the product with the local variables and associated parts,
     * then returns the user to the Main Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * */
    public void onActionSaveAddProduct(ActionEvent actionEvent) throws IOException {

        if (validateInput()) {

            try {

                // capture field data and save
                String name = productNameText.getText();
                double price = Double.parseDouble(productPriceText.getText());
                int stock = Integer.parseInt(productInvText.getText());
                int min = Integer.parseInt(productMinText.getText());
                int max = Integer.parseInt(productMaxText.getText());

                // create the product
                modifiedProduct.setName(name);
                modifiedProduct.setPrice(price);
                modifiedProduct.setStock(stock);
                modifiedProduct.setMin(min);
                modifiedProduct.setMax(max);

                // update the product
                Inventory.updateProduct(modifiedProduct.getId(), modifiedProduct);

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
     * This method instantiates a part from user selected part and adds the part to the global modifiedProduct's
     * Associated Parts List, then sets the Associated Parts TableView with the global modifiedProduct's Associated
     * Parts List.
     * @param actionEvent The event
     */
    public void onActionPartsListAddButton(ActionEvent actionEvent) {

        // generate part to be added
        Part partToBeAdded = productPartsListTableView.getSelectionModel().getSelectedItem();

        // add part
        modifiedProduct.addAssociatedPart(partToBeAdded);

        // set associated parts view
        productAssociatedPartsTableView.setItems(modifiedProduct.getAssociatedParts());

        productAssociatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Handles the Remove Part from Associated Parts TableView
     * This method instantiates a part from user selected part, validates that the global partList is not empty,
     * confirms the user's decision to remove, then removes the part from the global modifiedProduct's Associated Parts
     * List and the Associated Parts TableView.
     * @param actionEvent The event
     * @throws NullPointerException Thrown when an application attempts to use null in a case where an object is required.
     */
    public void onActionRemoveAssociatedPartButton(ActionEvent actionEvent) throws NullPointerException {

        // generate part to be removed from associated parts
        Part partToBeRemoved = productAssociatedPartsTableView.getSelectionModel().getSelectedItem();

        // execute if parts list is not null
        if (modifiedProduct.getAssociatedParts().size() > 0){

            // alert user to confirm delete
            Alert confirmDeleteProduct = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDeleteProduct.setTitle("Please Confirm Delete");
            Optional<ButtonType> input = confirmDeleteProduct.showAndWait();

            // execute part removal from associated parts list if user selects OK
            if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
                try {
                    modifiedProduct.getAssociatedParts().remove(partToBeRemoved);
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