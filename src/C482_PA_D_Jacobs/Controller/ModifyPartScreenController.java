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

    public void sendPart(Part part){

            partIDText.setText(String.valueOf(part.getId()));
            partNameText.setText(part.getName());
            partPriceCostText.setText(String.valueOf(part.getPrice()));
            partInvText.setText(String.valueOf(part.getStock()));
            partMinText.setText(String.valueOf(part.getMin()));
            partMaxText.setText(String.valueOf(part.getMax()));

            if (part.isInHouse()){
                machineIDLabel.setText("Machine ID");
            }
            else {
                machineIDLabel.setText("Company Name");
                outsourcedRadioButton.setSelected(true);
            }

            if (part instanceof InhousePart){

                partMachineIDText.setText(String.valueOf(((InhousePart) part).getMachineID()));
            }
            else {

                partMachineIDText.setText(((OutsourcedPart) part).getCompanyName());
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void onActionSaveModifyPart(ActionEvent actionEvent) throws IOException {

        if (partGroup.getSelectedToggle() == inhouseRadioButton){

//            // verify fields are not null
//            if ((partNameText.getText().trim().isEmpty() || partIDText.getText().trim().isEmpty() ||
//                    partPriceCostText.getText().trim().isEmpty() || partInvText.getText().trim().isEmpty() ||
//                    partMinText.getText().trim().isEmpty() || partMaxText.getText().trim().isEmpty() ||
//                    partMachineIDText.getText().trim().isEmpty())) {
//
//                // alert user that a field was left blank
//                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
//                confirmation.setTitle("Please enter information into all fields before saving.");
//                Optional<ButtonType> input = confirmation.showAndWait();
//
//                return;
//            }

             // capture field data and save
            int id = Integer.parseInt(partIDText.getText());
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceCostText.getText());
            int stock = Integer.parseInt(partInvText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineID = Integer.parseInt(partMachineIDText.getText());

            // create the inhouse part
            Part inhousePart = new InhousePart(id, name, price, stock, min, max, true, machineID);

            // update the inhouse part
            Inventory.updatePart(inhousePart.getId(), inhousePart);

        } //end if
        else if (partGroup.getSelectedToggle() == outsourcedRadioButton) {

//            // verify fields are not null
//            if ((partNameText.getText().trim().isEmpty() || partIDText.getText().trim().isEmpty() ||
//                    partPriceCostText.getText().trim().isEmpty() || partInvText.getText().trim().isEmpty() ||
//                    partMinText.getText().trim().isEmpty() || partMaxText.getText().trim().isEmpty() ||
//                    partMachineIDText.getText().trim().isEmpty())) {
//
//                // alert user that a field was left blank
//                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
//                confirmation.setTitle("Please enter information into all fields before saving.");
//                Optional<ButtonType> input = confirmation.showAndWait();
//
//                return;
//            }
//            else {
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
                    false, companyName);

            // update the outsourced part
            Inventory.updatePart(outsourcedPart.getId(), outsourcedPart);

            //}
        } //end else if

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    } //end method

    public void onActionCancelModifyPart(ActionEvent actionEvent) throws IOException {

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionInhouseRadioButton(ActionEvent actionEvent) {

        // if inhouse, change label to "Machine ID"
        machineIDLabel.setText("Machine ID");
    }

    public void onActionOutsourcedRadioButton(ActionEvent actionEvent) {

        // if outsourced, change label to "Company Name"
        machineIDLabel.setText("Company Name");
    }
}