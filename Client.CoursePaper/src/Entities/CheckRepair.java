package Entities;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.Objects;

public class CheckRepair implements Serializable
{

    private int repairID;
    private int employeeID;
    private int clientID;
    private String brand;
    private String model;
    private String repairType;
    private int status;
    private int validateByManager;
    private CheckBox stMech;
    private CheckBox stMan;

    public CheckRepair(int repairID, int employeeID, int clientID, String brand, String model, String repairType, int status, int validateByManager) {
        this.repairID = repairID;
        this.employeeID = employeeID;
        this.clientID = clientID;
        this.brand = brand;
        this.model = model;
        this.repairType = repairType;
        this.status = status;
        this.validateByManager = validateByManager;
        stMech=new CheckBox();
        stMech.setSelected(status!=0);
        stMan=new CheckBox();
        stMan.setSelected(validateByManager!=0);
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

    public CheckBox getStMech() {
        return stMech;
    }

    public void setStMech(CheckBox stMech) {
        this.stMech = stMech;
    }

    public CheckBox getStMan() {
        return stMan;
    }

    public void setStMan(CheckBox stMan) {
        this.stMan = stMan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckRepair that = (CheckRepair) o;
        return repairID == that.repairID &&
                employeeID == that.employeeID &&
                clientID == that.clientID &&
                status == that.status &&
                validateByManager == that.validateByManager &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(repairType, that.repairType) &&
                Objects.equals(stMech, that.stMech) &&
                Objects.equals(stMan, that.stMan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairID, employeeID, clientID, brand, model, repairType, status, validateByManager, stMech, stMan);
    }

    @Override
    public String toString() {
        return "CheckRepair{" +
                "repairID=" + repairID +
                ", employeeID=" + employeeID +
                ", clientID=" + clientID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", repairType='" + repairType + '\'' +
                ", status=" + status +
                ", validateByManager=" + validateByManager +
                ", stMech=" + stMech +
                ", stMan=" + stMan +
                '}';
    }
}
