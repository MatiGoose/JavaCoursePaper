package Repository;

import Server.Server;
import Entities.Employee;
import holder.EmployeeHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository
{
    public EmployeeRepository() {}

    public void createEmployeeRepository() {
        EmployeeHolder employeeHolder = EmployeeHolder.getInstance();
        try
        {
            Statement statement = Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from сотрудники");
            while (resultSet.next()) {
                employeeHolder.initialize(new Employee(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
