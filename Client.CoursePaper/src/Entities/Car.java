package Entities;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable
{
    private int carID;
    private String brand;
    private String model;
    private int year;
    private String engineType;
    private double engineCapacity;
    private String color;
    private int price;

    public Car(int carID, String brand, String model, int year, String engineType, double engineCapacity, String color, int price) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.color = color;
        this.price = price;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carID == car.carID &&
                year == car.year &&
                Double.compare(car.engineCapacity, engineCapacity) == 0 &&
                price == car.price &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(engineType, car.engineType) &&
                Objects.equals(color, car.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(carID, brand, model, year, engineType, engineCapacity, color, price);
    }
    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engineType='" + engineType + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
