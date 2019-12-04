package GUI.Manager.customerEditor;

import Entities.Customer;
import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerEditorController
{
    Customer customer;
    CommandsSender commandsSender;
    @FXML
    private TextField fio;
    @FXML
    private TextField passport;
    @FXML
    private TextField phone;

    public void init(Customer customer){
        this.customer=customer;
        commandsSender=new CommandsSender();
        fio.setText(customer.getFIO());
        passport.setText(customer.getPassportNumber());
        phone.setText(customer.getPhoneNumber());

    }
    @FXML
    private void editCustomer(){
        commandsSender.sendMessage("editCustomer/"+customer.getClientID()+"/"+fio.getText()+"/"+passport.getText()+
                "/"+phone.getText());
        Stage stage=(Stage) fio.getScene().getWindow();
        stage.close();
    }
}
