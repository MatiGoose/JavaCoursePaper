package Repository;

import Server.Server;
import Entities.Customer;
import holder.ClientHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepository
{
    public ClientRepository(){}
    public void createClientRepository()
    {
        ClientHolder clientHolder = ClientHolder.getInstance();
        try
        {
            Statement statement= Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from клиенты");
            while (resultSet.next()){
                clientHolder.initialize(new Customer(resultSet.getInt(1),
                        resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
