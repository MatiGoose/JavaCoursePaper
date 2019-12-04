package Observer;

import Interfaces.Observer;
import Server.Server;
import Entities.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeObserver implements Observer<Employee>
{
    @Override
    public void onAdd(Employee employee) {
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
        StringBuilder newEmployeeData = new StringBuilder();
        newEmployeeData.append("('" + employee.getEmployeeID() + "','" + employee.getFio() + "','"
                + employee.getSalary() + "','" + employee.getEmployeeUserID()+"')");
        String add = "INSERT INTO car_showroom.сотрудники " +
                "(ID_Сотрудника,ФИО, Оклад, ID_Пользователя) VALUES " + newEmployeeData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDelete(Employee employee) {
        String employeeID = "(ID_Сотрудника='" + employee.getEmployeeID() + "')";
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String delete = "DELETE FROM car_showroom.сотрудники WHERE " + employeeID;
        try {
            Statement statement = connection.createStatement();
            statement.execute(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdate(Employee newEmployee, Employee oldEmployee) {
        StringBuilder newEmployeeData = new StringBuilder();
        newEmployeeData.append("ФИО='" + newEmployee.getFio() + "', Оклад='" + newEmployee.getSalary() +
                "', ID_Пользователя='" + newEmployee.getEmployeeUserID() +
                "' WHERE (ID_Сотрудника='" + newEmployee.getEmployeeID() + "')");
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String update="UPDATE car_showroom.сотрудники SET " +newEmployeeData;
        try{
            Statement statement = connection.createStatement();
            statement.execute(update);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
