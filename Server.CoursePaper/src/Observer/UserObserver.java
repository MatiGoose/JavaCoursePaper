package Observer;

import Interfaces.Observer;
import Server.Server;
import Entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserObserver implements Observer<User>
{
    @Override
    public void onAdd(User user) {
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder newUserData = new StringBuilder();
        newUserData.append("('" + user.getUserID() + "','" + user.getLogin() + "','" +
                user.getPass() + "','" + user.getUserType() + "')");
        String add = "INSERT INTO car_showroom.пользователи (ID_Пользователя,Логин,Пароль,Тип_пользователя) VALUES" + newUserData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDelete(User user) {
        String userLogin = "(Логин='" + user.getLogin() + "');";
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String delete = "DELETE FROM car_showroom.пользователи WHERE" + userLogin;
        try {
            Statement statement = connection.createStatement();
            statement.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(User newUser, User oldUser) {
        StringBuilder newUserData = new StringBuilder();
        newUserData.append("Логин='" + newUser.getLogin() + "', " + "Пароль='" + newUser.getPass() + "', "
                + "Тип_пользователя='" + newUser.getUserType() + "' WHERE (Логин='" + oldUser.getLogin() + "')");
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String update = "UPDATE car_showroom.пользователи SET " + newUserData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
