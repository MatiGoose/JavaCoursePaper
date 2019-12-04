package Server;

import Client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import GUI.Main;
import GUI.Manager.main.MainManagerController;
import GUI.Mechanic.main.MainMechanicController;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoggingInstructions
{
    public void executeAuthInstructions(String serverAnswer) {
        String[] command = serverAnswer.split("/");
        if(!command[0].equals("Ошибка Авторизации")){
            //Main.setStageTitle("Автосалон");
            Main.getMainStage().setTitle("Автосалон");
            Client client = Client.getInstance();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                sb.append(command[i]+"/");
            }
            sb.deleteCharAt(sb.length()-1);
            client.setClientInfo(sb.toString());
        }
        switch (command[0]) {
            case "Ошибка Авторизации":
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(command[0]);
                alert.setHeaderText(null);
                alert.setContentText("Неправильный логин или пароль");
                alert.showAndWait();
                break;
            case "механик":
                try {
                    Parent root;
                    MainMechanicController mainMechanicController;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../GUI/Mechanic/main/MechanicMain.fxml"));
                    root = loader.load();
                    Main.getMainStage().setScene((new Scene(root, 900, 700)));
                    mainMechanicController = loader.getController();
                    mainMechanicController.init("Механик: " + command[2],"ID Механика: "+command[1]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                try {
                    FXMLLoader loader = new FXMLLoader();
                    Parent root;
                    MainManagerController mainManagerController;
                    loader.setLocation(getClass().getResource("../GUI/Manager/main/ManagerMain.fxml"));
                    root = loader.load();
                    Main.getMainStage().setScene((new Scene(root, 900, 700)));
                    mainManagerController = loader.getController();
                    if(command[0].equals("менеджер")){
                        mainManagerController.init("Менеджер: " + command[2],
                                "ID Менеджера: " + command[1]);
                    } else {
                        mainManagerController.init("Админ: " + command[2],
                                "ID Администратора: " + command[1]);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

        }


    }
}
