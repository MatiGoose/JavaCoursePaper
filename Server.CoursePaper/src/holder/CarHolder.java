package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.CarObserver;
import Entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarHolder implements Repository<Car>
{
    private static CarHolder instance;
    private ArrayList<Car> carList;
    private CarObserver observer = new CarObserver();


    public static CarHolder getInstance() {
        if (instance == null) {
            instance = new CarHolder();
        }
        return instance;
    }

    private CarHolder() {
        carList = new ArrayList<>();
    }

    public List<Car> getCarList() {
        return carList;
    }

    @Override
    public void add(Car car) {
        if (carList.size() < 1) {
            car.setCarID(1);
        } else {
            car.setCarID(carList.get(carList.size() - 1).getCarID() + 1);
        }
        carList.add(car);
        observer.onAdd(car);
    }

    public void initialize(Car car) {
        carList.add(car);

    }

    @Override
    public void remove(Car car) {
        carList.remove(car);
        observer.onDelete(car);
    }

    @Override
    public void update(Car newCar, Car oldCar) {
        int carPos = carList.indexOf(oldCar);
        newCar.setCarID(oldCar.getCarID());
        carList.set(carPos, newCar);
        observer.onUpdate(newCar, oldCar);
    }

    @Override
    public List<Car> query(Specification<Car> specification) {
        List<Car> cars = new ArrayList<>();
        for (Car car : carList) {
            if (specification.specify(car)) {
                cars.add(car);
            }
        }
        return cars;
    }
}
