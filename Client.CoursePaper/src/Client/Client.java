package Client;

import Entities.Employee;
import Server.LoggingInstructions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
    private static Client instance;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private String clientInfo;
    private Employee employee;


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    private Client(){
    }
    public static Client getInstance(){
        if(instance==null){
            instance=new Client();
        }
        return instance;
    }

    public void connectToServer(){
        try {
            clientSocket = new Socket("127.0.0.1", 3333);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void logIn(String userData){

        String serverAnswer;
        try {
            outputStream.writeObject(userData);
            serverAnswer=(String)inputStream.readObject();
            new LoggingInstructions().executeAuthInstructions(serverAnswer);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
