package GUI.Manager.order;

import GUI.Error.Error;
import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderController
{
    private CommandsSender commandsSender;
    @FXML
    private TextField carID;
    @FXML
    private TextField managerID;
    @FXML
    private TextField date;
    @FXML
    private TextField clientFIO;
    @FXML
    private TextField clientPassport;
    @FXML
    private TextField clientNumber;

    public void init(int carID){

        managerID.setText(Client.Client.getInstance().getClientInfo().split("/")[0]);
        if(carID!=0) {
            this.carID.setText("" + carID);
            this.carID.setEditable(false);
        }
        Date today=new Date();
        SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd");
        date.setText(outputFormat.format(today));
        commandsSender =new CommandsSender();
    }

    @FXML
    private void createPurchase(){
        try {
            String message;
            if (clientFIO.getText().equals("") || clientPassport.getText().equals("") || clientFIO.getText().equals("")) {
                Error.showAlert("Не все поля заполнены");
                return;
            }
            Integer.parseInt(carID.getText());
            Integer.parseInt(managerID.getText());
            message = "createPurchase/" + clientFIO.getText() + "/" + clientPassport.getText().toUpperCase() + "/" + clientNumber.getText() + "/" +
                    carID.getText() + "/" + managerID.getText() + "/" + date.getText();
            commandsSender.sendMessage(message);
            Stage stage = (Stage) carID.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e){
            Error.showAlert("Неправильный формат введённых данных");
            return;
        }
    }
}
