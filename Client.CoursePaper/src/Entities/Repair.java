package Entities;

import java.io.Serializable;
import java.util.Objects;

public class Repair implements Serializable
{
    private int repairID;
    private int employeeID;
    private int clientID;
    private String brand;
    private String model;
    private String repairType;
    private int status;
    private int validateByManager;

    public Repair(int repairID, int employeeID, int clientID, String brand, String model, String repairType, int status, int validateByManager) {
        this.repairID = repairID;
        this.employeeID = employeeID;
        this.clientID = clientID;
        this.brand = brand;
        this.model = model;
        this.repairType = repairType;
        this.status = status;
        this.validateByManager = validateByManager;
    }

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getValidateByManager() {
        return validateByManager;
    }

    public void setValidateByManager(int validateByManager) {
        this.validateByManager = validateByManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return repairID == repair.repairID &&
                employeeID == repair.employeeID &&
                clientID == repair.clientID &&
                status == repair.status &&
                validateByManager == repair.validateByManager &&
                Objects.equals(brand, repair.brand) &&
                Objects.equals(model, repair.model) &&
                Objects.equals(repairType, repair.repairType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairID, employeeID, clientID, brand, model, repairType, status, validateByManager);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairID=" + repairID +
                ", employeeID=" + employeeID +
                ", clientID=" + clientID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", repairType='" + repairType + '\'' +
                ", status=" + status +
                ", validateByManager=" + validateByManager +
                '}';
    }
}
