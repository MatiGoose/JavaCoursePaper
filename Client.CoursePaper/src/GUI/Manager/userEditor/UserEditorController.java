package GUI.Manager.userEditor;

import Entities.User;
import Server.CommandsSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditorController
{
    CommandsSender commandsSender;
    User user;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private ChoiceBox<String> type;
    public void init(User user){
        this.user=user;
        commandsSender=new CommandsSender();
        ObservableList<String> strings= FXCollections.observableArrayList("админ","механик","менеджер");
        type.setItems(strings);
        login.setText(user.getLogin());
        pass.setText(user.getPass());
        type.setValue(user.getUserType());
    }

    @FXML
    private void editUser(){
        commandsSender.sendMessage("editUser/"+user.getUserID()+"/"+login.getText()+"/"+pass.getText()+"/"+
                type.getValue());
        Stage stage=(Stage) login.getScene().getWindow();
        stage.close();
    }
}
