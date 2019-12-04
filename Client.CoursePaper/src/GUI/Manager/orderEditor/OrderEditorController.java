package GUI.Manager.orderEditor;

import Entities.Purchase;
import GUI.Error.Error;
import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderEditorController
{
    CommandsSender commandsSender;
    private Purchase purchase;
    @FXML
    private TextField carID;
    @FXML
    private TextField clientID;
    public void init(Purchase purchase){
        this.purchase=purchase;
        commandsSender=new CommandsSender();
        carID.setText(purchase.getCarID()+"");
        clientID.setText(purchase.getClientID()+"");
    }
    @FXML
    private void editOrder(){
        try {
            if(!carID.getText().equals("")) {
                Integer.parseInt(carID.getText());
            }
            if(!carID.getText().equals("")) {
                Integer.parseInt(clientID.getText());
            }
            commandsSender.sendMessage("editPurchase/" + purchase.getPurchaseID() + "/" + carID.getText() +
                    "/" + clientID.getText());
            Stage stage = (Stage) carID.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e){
            Error.showAlert("Неправильный формат введённых данных");
        }
    }
}
