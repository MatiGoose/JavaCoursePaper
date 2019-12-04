package GUI.Mechanic.repair;

import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RepairController
{
    private CommandsSender commandsSender;
    @FXML
    private TextField mechID;
    @FXML
    private TextField brand;
    @FXML
    private TextField model;
    @FXML
    private TextField repairType;
    @FXML
    private TextField clientFIO;
    @FXML
    private TextField passportNumber;
    @FXML
    private TextField number;
    public void init(){
        mechID.setText(Client.Client.getInstance().getClientInfo().split("/")[0]);
        commandsSender = new CommandsSender();
    }
    @FXML
    private void createRepair(){
        String message;
        message="createRepair/"+clientFIO.getText()+"/"+passportNumber.getText()+"/"+number.getText()
                +"/"+mechID.getText()+"/"+brand.getText()+"/"+model.getText()+"/"+repairType.getText()+"/0/0";
        commandsSender.sendMessage(message);
        Stage stage=(Stage) mechID.getScene().getWindow();
        stage.close();

    }



}
