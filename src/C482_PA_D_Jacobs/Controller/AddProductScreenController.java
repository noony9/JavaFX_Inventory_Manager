package C482_PA_D_Jacobs.Controller;

import C482_PA_D_Jacobs.Model.Inventory;
import C482_PA_D_Jacobs.Model.Part;
import C482_PA_D_Jacobs.Model.Product;
import javafx.event.ActionEvent;
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

public class AddProductScreenController implements Initializable {

    public TextField productInvText;
    public TextField productIDText;
    public TextField productMinText;
    public TextField productPriceText;
    public TextField productNameText;
    public TextField productMaxText;
    public TextField productSearchBox;
    public Button productPartsListAddButton;
    public Button productRemoveAssociatedPartButton;
    public Button productCancelButton;
    public Button productSaveButton;

    public TableView<Part> productPartsListTableView;
    public TableColumn<Part, Integer> productPartIDCol;
    public TableColumn<Part, String> productPartNameCol;
    public TableColumn<Part, Integer> productPartInvCol;
    public TableColumn<Part, Double> productPartPriceCol;

    public TableView<Part> productAssociatedPartsTableView;
    public TableColumn<Part, Integer> productAssociatedPartIDCol;
    public TableColumn<Part, String> productAssociatedPartNameCol;
    public TableColumn<Part, Integer> productAssociatedPartInvCol;
    public TableColumn<Part, Double> productAssociatedPartPriceCol;

    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
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

    public void onActionSaveAddProduct(ActionEvent actionEvent) throws IOException {

        // verify fields are not null
        if ((productNameText.getText().trim().isEmpty() || productIDText.getText().trim().isEmpty() ||
                productPriceText.getText().trim().isEmpty() || productInvText.getText().trim().isEmpty() ||
                productMinText.getText().trim().isEmpty() || productMaxText.getText().trim().isEmpty())) {

            // alert user that a field was left blank
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Please enter information into all fields before saving.");
            Optional<ButtonType> input = confirmation.showAndWait();

            return;
        }
        else {
            try {

                // capture field data and save
                String name = productNameText.getText();
                double price = Double.parseDouble(productPriceText.getText());
                int stock = Integer.parseInt(productInvText.getText());
                int min = Integer.parseInt(productMinText.getText());
                int max = Integer.parseInt(productMaxText.getText());

                // create the product
                Product product = new Product(name, price, stock, min, max);

                // update the product
                Inventory.addProduct(product);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // return to main screen
            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onActionCancelAddProduct(ActionEvent actionEvent) throws IOException {

        // return to main screen
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionAddPartToAssociatedPartTableViewButton(ActionEvent actionEvent) {
        /*
        // Initialize Associated Parts
        productAssociatedPartsTableView.setItems(Product.associatedParts);

        productAssociatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

         */
    }
}