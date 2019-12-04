package Repository;

import Server.Server;
import Entities.Car;
import holder.CarHolder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarRepository
{
    public CarRepository(){}
    public void createCarRepository() {
        CarHolder carHolder = CarHolder.getInstance();
        try
        {
            Statement statement= Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from автомобили");
            while (resultSet.next()){
                carHolder.initialize(new Car(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getInt(4),
                        resultSet.getString(5),resultSet.getDouble(6),
                        resultSet.getString(7),resultSet.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
