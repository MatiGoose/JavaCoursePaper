package GUI.Manager.repairEditor;

import Entities.CheckRepair;
import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class repairEditorController
{
    CommandsSender commandsSender;
    CheckRepair repair;
    @FXML
    private TextField brand;
    @FXML
    private TextField model;
    @FXML
    private TextField repairType;

    public void init(CheckRepair repair) {
        this.repair=repair;
        commandsSender=new CommandsSender();
        brand.setText(repair.getBrand());
        model.setText(repair.getModel());
        repairType.setText(repair.getRepairType());
    }
    @FXML
    private void editRepair(){
        commandsSender.sendMessage("editRepair/"+repair.getRepairID()+"/"+brand.getText()+
                "/"+model.getText()+"/"+repairType.getText());
        Stage stage = (Stage) brand.getScene().getWindow();
        stage.close();
    }
}
