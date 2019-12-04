package GUI.Manager.car;

import Entities.Car;
import GUI.Error.Error;
import Server.CommandsSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class carController
{
    Car car;
    CommandsSender executer;
    @FXML
    private TextField brand, model, color, price, capacity, year;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Label mainLabel;


    public void init(Car car) {
        ObservableList<String> types = FXCollections.observableArrayList("бензин", "дизель");
        type.setItems(types);
        executer=new CommandsSender();
        if(car!=null){
            this.car=car;
            addButton.setVisible(false);
            editButton.setVisible(true);
            brand.setText(car.getBrand());
            model.setText(car.getModel());
            color.setText(car.getColor());
            price.setText(""+car.getPrice());
            capacity.setText(""+car.getEngineCapacity());
            year.setText(""+car.getYear());
            type.setValue(car.getEngineType());
            mainLabel.setText("Изменение данных об автомобиле");
            mainLabel.setCenterShape(true);
        }
    }

    @FXML
    private void addCar() {
        try {
            Integer.parseInt(price.getText());
            Integer.parseInt(year.getText());
            Double.parseDouble(capacity.getText());
            if ((brand.getText().equals("") || model.getText().equals("") || color.getText().equals("") ||
                    price.getText().equals("") || capacity.getText().equals("") || year.getText().equals("") || type.getValue() == null)) {
                Error.showAlert("Не все поля заполнены");
                return;
            }
            String car = "addCar/" + brand.getText() + "/" + model.getText() + "/" + year.getText() + "/" + type.getValue() +
                    "/" + capacity.getText() + "/" + color.getText() + "/" + price.getText();
            executer.sendMessage(car);
            brand.setText("");
            model.setText("");
            color.setText("");
            price.setText("");
            capacity.setText("");
            year.setText("");
            type.setValue(null);
        } catch (NumberFormatException e){
            Error.showAlert("Неправильный формат введённых данных");
        }
    }
    @FXML
    private void editCar(){
        try {
            Integer.parseInt(price.getText());
            Integer.parseInt(year.getText());
            Double.parseDouble(capacity.getText());
            String message = "editCar/" + car.getCarID() + "/" + brand.getText() + "/" + model.getText() + "/" + year.getText()
                    + "/" + type.getValue() + "/" + capacity.getText() + "/" + color.getText() + "/" + price.getText();
            System.out.println(message);
            executer.sendMessage(message);
            Stage stage = (Stage) brand.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Error.showAlert("Неправильный формат введённых данных");
        }

    }
}
