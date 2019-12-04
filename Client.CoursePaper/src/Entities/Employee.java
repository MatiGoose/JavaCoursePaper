package Entities;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable
{
    private int employeeID;
    private String fio;
    private int salary;
    private int employeeUserID;

    public Employee(int employeeID, String fio, int salary, int employeeUserID) {
        this.employeeID = employeeID;
        this.fio = fio;
        this.salary = salary;
        this.employeeUserID = employeeUserID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employee_id) {
        this.employeeID = employeeID;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmployeeUserID() {
        return employeeUserID;
    }

    public void setEmployeeUserID(int employeeUserID) {
        this.employeeUserID = employeeUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID &&
                salary == employee.salary &&
                employeeUserID == employee.employeeUserID &&
                Objects.equals(fio, employee.fio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(employeeID, fio, salary, employeeUserID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employeeID +
                ", fio='" + fio + '\'' +
                ", salary=" + salary +
                ", employeeUserID=" + employeeUserID +
                '}';
    }
}
