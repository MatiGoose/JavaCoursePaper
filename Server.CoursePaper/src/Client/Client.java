package Client;

import Server.CommandExecuter;
import Specifications.employee.EmployeeSpecification;
import Specifications.user.UserSpecification;
import Entities.Employee;
import Entities.User;
import holder.EmployeeHolder;
import holder.UserHolder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.List;


public class Client implements Runnable
{
    private static final String url = "jdbc:mysql://localhost:3306/business?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "1311";

    private Boolean SignedIn = false;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    final Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    private CommandExecuter commandExecuter;

    // Constructor
    public Client(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream)
    {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.socket = socket;
        commandExecuter = new CommandExecuter(outputStream);
    }
    private void SignIn()
    {
        StringBuilder message=new StringBuilder();
        String clientData[] = new String[2];
        List<User> userList;
        List<Employee> employeeList;
        try
        {
            clientData[0] = (String) inputStream.readObject();
            clientData = clientData[0].split(" ");
            userList = UserHolder.getInstance().query(new UserSpecification(clientData[0], clientData[1]));
            if (userList.size() > 0) {
                employeeList= EmployeeHolder.getInstance().
                        query(new EmployeeSpecification(userList.get(0).getUserID()));
                message.append(userList.get(0).getUserType()+"/"+employeeList.get(0).getEmployeeID()+"/"+
                        employeeList.get(0).getFio()+"/"+employeeList.get(0).getSalary()+"/"
                        +employeeList.get(0).getEmployeeUserID());
                SignedIn = true;

            } else {
                message.append("Ошибка Авторизации/");
            }
            outputStream.writeObject(message.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void run()
    {
        String message;
        SignIn();
        if(!SignedIn)
        {
            return;
        }
        try
        {
            while(true)
            {
                message = (String)inputStream.readObject();
                commandExecuter.executeCommand(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
