package GUI.Logging;

import Client.Client;
import javafx.scene.control.TextField;

public class LoginController
{

    public TextField User_Login;
    public TextField User_Password;

    public void LoginButton()
    {
        StringBuilder userData = new StringBuilder();
        userData.append(User_Login.getText() + " " + User_Password.getText());
        Client client = Client.getInstance();
        client.connectToServer();
        client.logIn(userData.toString());
    }
}
