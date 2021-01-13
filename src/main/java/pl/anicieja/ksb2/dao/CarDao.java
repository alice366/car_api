package pl.anicieja.ksb2.dao;

import pl.anicieja.ksb2.model.car.Car;

import java.util.List;

public interface CarDao {

    void saveCar(long id, String brand, String model, String colour, long year);

    List<Car> findAll();

    List<Car> getCarsByColour(String colour);

    List<Car> getCarsByYear(Long yearStart, Long yearEnd);

    Car getCarById(long id);

    void updateCar(Car newCar);

    void deleteCar(long id);
}
