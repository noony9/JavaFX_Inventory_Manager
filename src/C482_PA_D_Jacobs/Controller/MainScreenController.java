package C482_PA_D_Jacobs.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    public void onActionExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddModifyPartScreen.fxml"));
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
            //FIXME
        }

    }

    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../View/AddModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteProduct(ActionEvent actionEvent) {

        // alert user to confirm delete
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Please Confirm Delete");
        Optional<ButtonType> input = confirmation.showAndWait();

        // execute deletion if user confirms
        if ((input.isPresent()) && (input.get() == ButtonType.OK)) {
            //FIXME
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
