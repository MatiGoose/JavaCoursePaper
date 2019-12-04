package Observer;

import Interfaces.Observer;
import Server.Server;
import Entities.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientObserver implements Observer<Customer>
{
    @Override
    public void onAdd(Customer customer) {
        Server server = Server.GetInstance();
        Connection connection = null;
        try
        {
            connection = server.GetConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        StringBuilder newCustomerData = new StringBuilder();
        newCustomerData.append("('" + customer.getClientID() + "','" + customer.getFIO() + "','"
                + customer.getPassportNumber() + "','" + customer.getPhoneNumber() + "')");
        String add = "INSERT INTO car_showroom.клиенты (ID_Клиента,ФИО, Номер_паспорта, Номер_телефона) " +
                "VALUES " + newCustomerData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDelete(Customer customer) {
        String customerID = "(ID_Клиента='" + customer.getClientID() + "')";
        Server server = Server.GetInstance();
        Connection connection = null;
        try
        {
            connection = server.GetConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String delete = "DELETE FROM car_showroom.клиенты WHERE " + customerID;
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(delete);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdate(Customer newCustomer, Customer oldCustomer) {
        StringBuilder newCustomerData = new StringBuilder();
        newCustomerData.append("ФИО='" + newCustomer.getFIO() + "', Номер_паспорта='" + newCustomer.getPassportNumber()
                + "', Номер_телефона='" + newCustomer.getPhoneNumber()
                + "' WHERE (ID_Клиента='" + newCustomer.getClientID() + "')");
        Server server = Server.GetInstance();
        Connection connection = null;
        try
        {
            connection = server.GetConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        String update = "UPDATE car_showroom.клиенты SET " + newCustomerData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
