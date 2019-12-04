package GUI;

import Server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    Server Server;
    public TextField Server_Address;
    public TextField Server_Port;
    public TextField DataBase_Name;
    public TextField DataBase_Port;
    public TextField DataBase_Password;
    public TextField DataBase_Username;
    public Button Server_Launch;
    public Button Server_Shutdown;

    public void LaunchServer(ActionEvent event) throws IOException {
        Server_Launch.setVisible(false);
        Server_Shutdown.setVisible(true);
        String tmp = Server_Address.getText();
        Server = new Server(Server_Address.getText(), Server_Port.getText(), DataBase_Name.getText(), DataBase_Port.getText(), DataBase_Password.getText(), DataBase_Username.getText());
        Server.GoOnline();
    }
    public void ShutdownServer(ActionEvent event)
    {
        Server_Shutdown.setVisible(false);
        Server_Launch.setVisible(true);
        Server.Shutdown();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
