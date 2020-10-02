package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.InhousePart;
import C482_PA_D_Jacobs.Model.OutsourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddModifyPartScreenController implements Initializable {
    public ToggleGroup partGroup;
    public Label machineIDLabel;
    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField addPartMaxText;
    @FXML
    private TextField addPartMachineIDText;
    @FXML
    private TextField addPartMinText;
    @FXML
    private TextField addPartPriceCostText;
    @FXML
    private TextField addPartInvText;
    @FXML
    private TextField addPartNameText;
    @FXML
    private TextField addPartIDText;

    Stage stage;
    Parent scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionSaveAddPart(ActionEvent actionEvent) throws IOException {

        if (partGroup.getSelectedToggle() == inhouseRadioButton){
            try {
                // capture field data and save
                int id = Integer.parseInt(addPartIDText.getText());
                String name = addPartNameText.getText();
                double price = Double.parseDouble(addPartPriceCostText.getText());
                int stock = Integer.parseInt(addPartInvText.getText());
                int min = Integer.parseInt(addPartMinText.getText());
                int max = Integer.parseInt(addPartMaxText.getText());
                int machineID = Integer.parseInt(addPartMachineIDText.getText());

                // verify fields are not null
                if ((String.valueOf(name).isEmpty() || String.valueOf(id).isEmpty() || String.valueOf(price).isEmpty()
                        || String.valueOf(stock).isEmpty() || String.valueOf(min).isEmpty() ||
                        String.valueOf(max).isEmpty() || String.valueOf(machineID).isEmpty())) {

                    // alert user that a field was left blank
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Please enter information into all fields before saving.");
                    Optional<ButtonType> input = confirmation.showAndWait();

                    return;
                }
                else {

                    // create the inhouse part
                    InhousePart inhouse = new InhousePart(id, name, price, stock, min, max, machineID);

                    // add the inhouse part
                    //FIXME
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } //end if
        else if (partGroup.getSelectedToggle() == outsourcedRadioButton) {

            // update the label machineID to companyName
            machineIDLabel.setText("Company Name");

            try {
                // capture field data and save
                int id = Integer.parseInt(addPartIDText.getText());
                String name = addPartNameText.getText();
                double price = Double.parseDouble(addPartPriceCostText.getText());
                int stock = Integer.parseInt(addPartInvText.getText());
                int min = Integer.parseInt(addPartMinText.getText());
                int max = Integer.parseInt(addPartMaxText.getText());
                String companyName = addPartMachineIDText.getText();


                // verify fields are not null
                if ((String.valueOf(name).isEmpty() || String.valueOf(id).isEmpty() || String.valueOf(price).isEmpty()
                        || String.valueOf(stock).isEmpty() || String.valueOf(min).isEmpty() ||
                        String.valueOf(max).isEmpty() || String.valueOf(companyName).isEmpty())) {

                    // alert user that a field was left blank
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Please enter information into all fields before saving.");
                    Optional<ButtonType> input = confirmation.showAndWait();

                    return;
                } else {

                    // create the outsourced part
                    OutsourcedPart outsourced = new OutsourcedPart(id, name, price, stock, min, max, companyName);

                    // add the outsourced part
                    //FIXME
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } //end else if

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    } //end method

    public void onActionCancelAddPart(ActionEvent actionEvent) throws IOException {

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
