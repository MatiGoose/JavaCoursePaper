package Repository;

import Server.Server;
import Entities.User;
import holder.UserHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository
{
    public UserRepository() {}
    public void createUserRepository()
    {
        UserHolder userHolder = UserHolder.getInstance();
        try {
            Statement statement = Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet=statement.executeQuery("select * from пользователи");
            while(resultSet.next()){
                userHolder.initialize(new User(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4)));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
