package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.InhousePart;
import C482_PA_D_Jacobs.Model.Inventory;
import C482_PA_D_Jacobs.Model.OutsourcedPart;
import C482_PA_D_Jacobs.Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the controller to be used to control the flow of data into the Modify Part Screen and updates the view
 * as required.
 * */
public class ModifyPartScreenController implements Initializable {

    public ToggleGroup partGroup;
    public Label machineIDLabel;
    public RadioButton inhouseRadioButton;
    public RadioButton outsourcedRadioButton;
    public TextField partMachineIDText;
    @FXML
    private TextField partMaxText;
    @FXML
    private TextField partMinText;
    @FXML
    private TextField partPriceCostText;
    @FXML
    private TextField partInvText;
    @FXML
    private TextField partNameText;
    @FXML
    private TextField partIDText;

    Stage stage;
    Parent scene;

    /** This method is used to transfer part data to the Modify Part Screen as well as set Inhouse and Outsourced
     * unique label and toggle.
     * This method first sets the Modify Part Screen TextFields using the part selected for transfer, then sets the
     * respective special labels and toggle.
     * @param part The part to be transferred
     * @see MainScreenController#onActionModifyPart(ActionEvent)
     */
    public void sendPart(Part part){

            partIDText.setText(String.valueOf(part.getId()));
            partNameText.setText(part.getName());
            partPriceCostText.setText(String.valueOf(part.getPrice()));
            partInvText.setText(String.valueOf(part.getStock()));
            partMinText.setText(String.valueOf(part.getMin()));
            partMaxText.setText(String.valueOf(part.getMax()));

            if (part instanceof InhousePart){
                machineIDLabel.setText("Machine ID");
                partMachineIDText.setText(String.valueOf(((InhousePart) part).getMachineID()));
            }
            else {
                machineIDLabel.setText("Company Name");
                outsourcedRadioButton.setSelected(true);
                partMachineIDText.setText(((OutsourcedPart) part).getCompanyName());
            }
    }

    /**
     * Initializes the controller class.
     * @param url Uniformed Resource Locator
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /** Handles the save event.
     * This method first validates user input in all fields, assigns a unique ID, captures input in the TextFields
     * into local variables, creates a new Part, updates the part with the local variables, then returns the user
     * to the Main Screen.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * */
    public void onActionSaveModifyPart(ActionEvent actionEvent) throws IOException {

        // Programmed into this code block is an input validator method that is inserted as a gatekeeper to prevent
        // common runtime IO exceptions from user input and controlling the user's ability to save data entered into
        // form fields until user input meets specifications defined in the validator method and assists the user in
        // identifying appropriate input by using pop-up warnings to guide input.  The onActionSaveModifyPart method will
        // throw an IOException to catch any exceptional cases of user input not prevented by the input validator method.
        if(validateInput()) {
            if (partGroup.getSelectedToggle() == inhouseRadioButton){

                // capture field data and save
                int id = Integer.parseInt(partIDText.getText());
                String name = partNameText.getText();
                double price = Double.parseDouble(partPriceCostText.getText());
                int stock = Integer.parseInt(partInvText.getText());
                int min = Integer.parseInt(partMinText.getText());
                int max = Integer.parseInt(partMaxText.getText());
                int machineID = Integer.parseInt(partMachineIDText.getText());

                // create the inhouse part
                Part inhousePart = new InhousePart(id, name, price, stock, min, max, machineID);

                // update the inhouse part
                Inventory.updatePart(inhousePart.getId(), inhousePart);

            } //end if
            else if (partGroup.getSelectedToggle() == outsourcedRadioButton) {

                // capture field data and save
                int id = Integer.parseInt(partIDText.getText());
                String name = partNameText.getText();
                double price = Double.parseDouble(partPriceCostText.getText());
                int stock = Integer.parseInt(partInvText.getText());
                int min = Integer.parseInt(partMinText.getText());
                int max = Integer.parseInt(partMaxText.getText());
                String companyName = partMachineIDText.getText();

                // create the outsourced part
                Part outsourcedPart = new OutsourcedPart(id, name, price, stock, min, max,
                        companyName);

                // update the outsourced part
                Inventory.updatePart(outsourcedPart.getId(), outsourcedPart);

            } //end else if

            // return to main screen
            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    } //end method

    /** Handles the cancel event.
     * This method returns the user to the Main Screen without saving any of the data modifications.
     * @param actionEvent The event
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * */
    public void onActionCancelModifyPart(ActionEvent actionEvent) throws IOException {

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Handles the Inhouse Radio Button.
     * This method toggles the radio button to Inhouse, then changes the part's special label accordingly.
     * This method's event is used with the Add Part Screen's save event to instantiate the part as InhousePart and to
     * set the special part label accordingly.
     * @param actionEvent The event
     * @see ModifyPartScreenController#onActionSaveModifyPart(ActionEvent)
     */
    public void onActionInhouseRadioButton(ActionEvent actionEvent) {

        // if inhouse, change label to "Machine ID"
        machineIDLabel.setText("Machine ID");
    }

    /** Handles the Outsourced Radio Button.
     * This method toggles the radio button to Outsourced, then changes the part's special label accordingly.
     * This method's event is used with the Modify Part Screen's save event to instantiate the part as OutsourcedPart and
     * to set the special part label accordingly.
     * @param actionEvent The event
     * @see ModifyPartScreenController#onActionSaveModifyPart(ActionEvent)
     */
    public void onActionOutsourcedRadioButton(ActionEvent actionEvent) {

        // if outsourced, change label to "Company Name"
        machineIDLabel.setText("Company Name");
    }

    /** This method is the application's de facto input validation method used by the Add and Modify views' save method
     * to ensure valid input is provided prior to proceeding with instancing or updating the parts.
     * This method validates in this order: ensure no fields are empty (ID is auto-generated and is left out of all
     * validation); ensure no inappropriate chars are present; ensure min less than max; ensure inventory is greater
     * than or equal to min and less than or equal to max; and ensure that a double (from 1.0 - 999,999) is entered as
     * the price.
     * @return true if input in all fields are valid or false if not.
     * @see AddPartScreenController#onActionSaveAddPart(ActionEvent)
     * @see ModifyPartScreenController#onActionSaveModifyPart(ActionEvent)
     */
    public boolean validateInput() {

        // validate input present in all text fields
        if ((partNameText.getText().trim().isEmpty() || partPriceCostText.getText().trim().isEmpty()
                || partInvText.getText().trim().isEmpty() || partMinText.getText().trim().isEmpty() ||
                partMaxText.getText().trim().isEmpty() || partMachineIDText.getText().trim().isEmpty())) {

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

        if ((!partNameText.getText().matches(alphaNumericWithSpace) ||
                !partPriceCostText.getText().matches(numericWithDecimal ) ||
                !partInvText.getText().matches(numeric) ||
                !partMinText.getText().matches(numeric) ||
                !partMaxText.getText().matches(numeric)))  {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Please do not enter special characters or letters where numbers are expected " +
                    "or vice-versa.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate min less than max
        else if (Integer.parseInt(partMinText.getText()) > Integer.parseInt(partMaxText.getText())) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Min must be less than max.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate inventory is greater than or equal to min and less than or equal to max
        else if (Integer.parseInt(partInvText.getText()) < Integer.parseInt(partMinText.getText()) ||
                Integer.parseInt(partInvText.getText()) > Integer.parseInt(partMaxText.getText())) {

            // alert user of error
            Alert confirmation = new Alert(Alert.AlertType.ERROR);
            confirmation.setTitle("Input Error");
            confirmation.setContentText("Inventory must be greater than or equal to min and less than or equal to max.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return false;
        }

        // validate expected double data type (up to 999,999 or 999,999.99)
        else if (!(partPriceCostText.getText().matches("[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]\\.[0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]\\.[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9]|9\\.9") ||
                partPriceCostText.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]|9\\.9"))) {

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