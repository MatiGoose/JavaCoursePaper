package Entities;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable
{
    private int clientID;
    private String FIO;
    private String passportNumber;
    private String phoneNumber;

    public Customer(int clientID, String FIO, String passportNumber, String phoneNumber) {
        this.clientID = clientID;
        this.FIO = FIO;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return clientID == customer.clientID &&
                Objects.equals(FIO, customer.FIO) &&
                Objects.equals(passportNumber, customer.passportNumber) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "clientID=" + clientID +
                ", FIO='" + FIO + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
