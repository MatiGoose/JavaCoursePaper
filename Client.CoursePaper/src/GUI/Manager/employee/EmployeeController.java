package GUI.Manager.employee;

import GUI.Error.Error;
import Server.CommandsSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EmployeeController
{
    private CommandsSender commandsSender;
    @FXML
    private TextField fio;
    @FXML
    private TextField salary;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private ChoiceBox<String> type;

    public void init() {
        commandsSender = new CommandsSender();
        ObservableList<String> strings = FXCollections.observableArrayList("админ", "менеджер", "механик");
        type.setItems(strings);

    }

    @FXML
    private void addUser() {
        try {
            Integer.parseInt(salary.getText());
            if (fio.getText().equals("") || salary.getText().equals("") || login.getText().equals("") ||
                    pass.getText().equals("") || type.getValue().equals(null)) {
                Error.showAlert("Не все поля заполнены");
                return;
            }
            commandsSender.sendMessage("newUser/" + login.getText() + "/" + pass.getText() + "/" + type.getValue() +
                    "/" + fio.getText() + "/" + salary.getText());
            fio.setText("");
            salary.setText("");
            login.setText("");
            type.setValue(null);
            pass.setText("");
        }catch (NumberFormatException e){
            Error.showAlert("Неправильный формат ввода данных");
            return;
        }
    }
}
