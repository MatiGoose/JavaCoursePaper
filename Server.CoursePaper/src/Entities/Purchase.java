package Entities;

import java.io.Serializable;
import java.util.Objects;

public class Purchase implements Serializable
{
    private int purchaseID;
    private int carID;
    private int clientID;
    private int employeeID;
    private String date;

    public Purchase(int purchaseID, int carID, int clientID, int employeeID, String date) {
        this.purchaseID = purchaseID;
        this.carID = carID;
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.date = date;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return purchaseID == purchase.purchaseID &&
                carID == purchase.carID &&
                clientID == purchase.clientID &&
                employeeID == purchase.employeeID &&
                Objects.equals(date, purchase.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, carID, clientID, employeeID, date);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", carID=" + carID +
                ", clientID=" + clientID +
                ", employeeID=" + employeeID +
                ", date='" + date + '\'' +
                '}';
    }
}
