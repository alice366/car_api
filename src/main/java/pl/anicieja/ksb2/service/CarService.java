package pl.anicieja.ksb2.service;

import pl.anicieja.ksb2.model.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars() ;
    Optional<Car> getCarById(long id);
    List<Car> getCarsByColour(String colour);
    Boolean addCar(Car car);
    void deleteCarById(long id);

}
