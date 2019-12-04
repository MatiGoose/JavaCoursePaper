package Server;


import Client.Client;
import Repository.*;
import holder.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server implements Runnable
{
    //private final static Logger logger = LogManager.getLogger();
    private static Server instance;
    ServerSocket ServerSocket;
    private String Server_Adress;
    private String Server_Port;
    private String DataBase_Name;
    private String DataBase_Port;
    private String DataBase_Password;
    private String DataBase_Username;
    private static Connection connection;
    private boolean isOnline = false;
    //public static Logger getLogger(){
    //    return logger;
    //}
    public Server()
    {
        this.Server_Adress = null;
        this.Server_Port = null;
        this.DataBase_Name = null;
        this.DataBase_Port = null;
        this.DataBase_Password = null;
        this.DataBase_Username = null;
    }
    public Server(String Server_Adress, String Server_Port, String DataBase_Name, String DataBase_Port, String DataBase_Password, String DataBase_Username)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Driver driver = new oracle.jdbc.OracleDriver();
        //DriverManager.registerDriver(driver);

        this.Server_Adress = Server_Adress;
        this.Server_Port = Server_Port;
        this.DataBase_Name = DataBase_Name;
        this.DataBase_Port = DataBase_Port;
        this.DataBase_Password = DataBase_Password;
        this.DataBase_Username = DataBase_Username;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.Server_Adress +":" + this.DataBase_Port + "/" + this.DataBase_Name + "?autoReconnect=true&useSSL=false", this.DataBase_Username, this.DataBase_Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void GoOnline() throws IOException
    {
        /*isOnline = true;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + Server_Adress +":" + DataBase_Port + "/" + DataBase_Name + "?autoReconnect=true&useSSL=false", DataBase_Username, DataBase_Password);
        } catch (SQLException ex) {
            //Logger.log(Level.ERROR," Ошибка подключения к базе данных");
        }
        try {
            ServerSocket = new ServerSocket(Integer.parseInt(Server_Port));
        } catch (IOException ex) {
            //Logger.log(Level.ERROR,"Ошибка создания серверного сокета");
        }
        new UserRepository().createUserRepository();
        new CarRepository().createCarRepository();
        new ClientRepository().createClientRepository();
        new EmployeeRepository().createEmployeeRepository();
        new PurchaseRepository().createPurchaseRepository();
        new RepairRepository().createRepairRepository();

        new Thread(this).run();
        */

        isOnline = true;
        new UserRepository().createUserRepository();
        new CarRepository().createCarRepository();
        new ClientRepository().createClientRepository();
        new EmployeeRepository().createEmployeeRepository();
        new PurchaseRepository().createPurchaseRepository();
        new RepairRepository().createRepairRepository();

        //isOnline = true;
        //Connection = DriverManager.getConnection("jdbc:mysql://" + Server_Adress +":" + DataBase_Port + "/" + DataBase_Name + "?autoReconnect=true&useSSL=false", DataBase_Username, DataBase_Password);

        ServerSocket = new ServerSocket(Integer.parseInt(Server_Port));
        new Thread(this).start();
        /*while (true)
        {
            Socket receivedSocket = null;
            try
            {
                receivedSocket = ServerSocket.accept();

                ObjectInputStream is = new ObjectInputStream(receivedSocket.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(receivedSocket.getOutputStream());

                Thread Client_Thread = new Client(receivedSocket, os, is);
                Client_Thread.run();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/
    }
    public Connection GetConnection() throws SQLException
    {
        //String tmp = "jdbc:mysql://" + this.Server_Adress +":" + this.DataBase_Port + "/" + this.DataBase_Name + "?autoReconnect=true&useSSL=false";
        return connection;
    }
    public static Server GetInstance()
    {
        if(instance == null)
            instance = new Server();
        return instance;
    }
    public void Shutdown()
    {
        //isOnline = false;

        try {
            ServerSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        UserHolder.getInstance().getUserList().clear();
        CarHolder.getInstance().getCarList().clear();
        ClientHolder.getInstance().getCustomerList().clear();
        EmployeeHolder.getInstance().getEmployeeList().clear();
        PurchaseHolder.getInstance().getPurchaseList().clear();
        RepairHolder.getInstance().getRepairList().clear();
    }


    @Override
    public void run()
    {
        Socket receivedSocket = null;
        while (isOnline) {
            try {
                receivedSocket = ServerSocket.accept();
                ObjectInputStream is = new ObjectInputStream(receivedSocket.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(receivedSocket.getOutputStream());

                new Thread(new Client(receivedSocket, os, is)).start();
            } catch (IOException ex) {
                //logger.log(Level.INFO,ex.getMessage());
            }
        }
    }
}
