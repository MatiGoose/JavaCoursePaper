package Observer;

import Interfaces.Observer;
import Entities.Car;
import Server.Server;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CarObserver implements Observer<Car>
{
    @Override
    public void onAdd(Car car)
    {
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
        StringBuilder newCarData = new StringBuilder();
        newCarData.append("('" + car.getCarID() + "','" + car.getBrand() + "','" + car.getModel() + "','"
                + car.getYear() + "','" + car.getEngineType() + "','"
                + car.getEngineCapacity() + "','" + car.getColor() + "','" + car.getPrice() + "')");
        String add = "INSERT INTO car_showroom.автомобили " +
                "(Код_Автомобиля,Марка, Модель, Год_выпуска, Тип_двигателя, Объём_двигателя, Цвет, Цена) " +
                "VALUES " + newCarData;
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(add);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onDelete(Car car) {
        String carId = "(Код_Автомобиля='" + car.getCarID() + "')";
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
        String delete = "DELETE FROM car_showroom.автомобили WHERE" + carId;
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(delete);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(Car newCar, Car oldCar)
    {
        StringBuilder newCarData = new StringBuilder();
        newCarData.append("Марка='" + newCar.getBrand() + "', Модель='" + newCar.getModel() +
                "', Год_выпуска='" + newCar.getYear() + "', Тип_двигателя='" + newCar.getEngineType() +
                "', Объём_двигателя='" + newCar.getEngineCapacity() + "', Цвет='" + newCar.getColor() +
                "', Цена='" + newCar.getPrice() + "' WHERE (Код_Автомобиля='" + newCar.getCarID() + "')");
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
        String update = "UPDATE car_showroom.автомобили SET " + newCarData;
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(update);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
