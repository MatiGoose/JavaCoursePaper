package Server;

import Entities.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CommandsSender
{
    int i;
    public CommandsSender() {
        outputStream = Client.Client.getInstance().getOutputStream();
        inputStream = Client.Client.getInstance().getInputStream();
    }

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ArrayList<Car> getCarList() {
        ArrayList<Car> carList = new ArrayList<>();
        try {
            outputStream.writeObject("getCarList/");
            carList=(ArrayList<Car>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public ArrayList<Purchase> getPurchaseList() {
        ArrayList<Purchase> purchases = new ArrayList<>();
        try
        {
            String employeeID = Client.Client.getInstance().getClientInfo().split("/")[0];
            outputStream.writeObject("getPurchaseList/" + employeeID);
            purchases = (ArrayList<Purchase>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return purchases;
    }

    public ArrayList<CheckRepair> getRepairList() {
        ArrayList<Repair> repairs = new ArrayList<>();
        try {
            String employeeID = Client.Client.getInstance().getClientInfo().split("/")[0];
            outputStream.writeObject("getRepairList/" + employeeID);
            repairs = (ArrayList<Repair>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<CheckRepair> checkRepairs= Converter.Converter.convertRepairToCheck(repairs);
        return  checkRepairs;
    }

    public ArrayList<CheckRepair> getAllRepairs() {
        ArrayList<Repair> repairs = new ArrayList<>();
        try {
            outputStream.writeObject("getAllRepairs/");
            repairs = (ArrayList<Repair>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<CheckRepair> checkRepairs= Converter.Converter.convertRepairToCheck(repairs);
        return  checkRepairs;
    }

    public ArrayList<Purchase> getAllPurchases() {
        ArrayList<Purchase> purchases = new ArrayList<>();
        try {
            outputStream.writeObject("getAllOrders/");
            purchases = (ArrayList<Purchase>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return purchases;
    }
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees=new ArrayList<>();
        try{
            outputStream.writeObject("getAllEmployees/");
            employees=(ArrayList<Employee>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    } public ArrayList<User> getUsers(){
    ArrayList<User> users=new ArrayList<>();
    try{
        outputStream.writeObject("getUsers/");
        users=(ArrayList<User>) inputStream.readObject();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return users;
}
    public ArrayList<Customer> getCustomers(){
        ArrayList<Customer> customers=new ArrayList<>();
        try{
            outputStream.writeObject("getCustomers/");
            customers=(ArrayList<Customer>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public void sendMessage(String car){
        try {
            outputStream.writeObject(car);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
