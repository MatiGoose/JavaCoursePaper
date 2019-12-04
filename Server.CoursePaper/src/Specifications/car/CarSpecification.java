package Specifications.car;

import Interfaces.Specification;
import Entities.Car;

public class CarSpecification implements Specification<Car> {
    private int carID;

    public CarSpecification(int carID){
        this.carID=carID;
    }

    @Override
    public boolean specify(Car car) {
        return carID==car.getCarID();
    }
}
