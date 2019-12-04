package Repository;

import Server.Server;
import Entities.Repair;
import holder.RepairHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepairRepository
{
    public RepairRepository(){

    }
    public void createRepairRepository(){
        RepairHolder repairHolder = RepairHolder.getInstance();
        try {
            Statement statement = Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ремонт");
            while (resultSet.next()) {
                repairHolder.initialize(new Repair(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getInt(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getString(6),
                        resultSet.getInt(7),resultSet.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
