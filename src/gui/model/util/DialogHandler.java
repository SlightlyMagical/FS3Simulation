package gui.model.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogHandler {

    /**
     * Displays a confirmation alert with the provided message
     */
    public static boolean confirmationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }

    /**
     * Displays an information alert with the provided message
     */
    public static void informationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.show();
    }

    /**
     * Displays an input dialog
     * @return the user input
     */
    public static String inputDialog(String text){
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText(text);
        Optional<String> result = inputDialog.showAndWait();
        return result.orElse("");
    }
}
