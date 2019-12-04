package GUI.Manager.employeeEditor;

import Entities.Employee;
import GUI.Error.Error;
import Server.CommandsSender;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class employeeEditorController
{
    CommandsSender commandsSender;
    Employee employee;
    @FXML
    private TextField fio;
    @FXML
    private TextField salary;
    public void init(Employee employee){
        commandsSender=new CommandsSender();
        this.employee=employee;
        fio.setText(employee.getFio());
        salary.setText(employee.getSalary()+"");
    }

    @FXML
    private void editEmployee(){
        try{
            if(!salary.getText().equals("")) {
                Integer.parseInt(salary.getText());
            }
            String message="editEmployee/"+employee.getEmployeeID()+"/"+fio.getText()+"/"+salary.getText();
            commandsSender.sendMessage(message);
        } catch (NumberFormatException e){
            Error.showAlert("Неправильный формат введённых данных");
        }
        Stage stage=(Stage) fio.getScene().getWindow();
        stage.close();

    }
}
