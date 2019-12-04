package GUI.Error;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Error
{
    public static void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static int showConfirmationAlert(String message){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вы желаете продолжить?");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return 1;
        } else {
            return 0;
        }

    }
}
