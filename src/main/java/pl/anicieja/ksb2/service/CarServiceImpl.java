package pl.anicieja.ksb2.service;

import org.springframework.stereotype.Service;
import pl.anicieja.ksb2.model.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    List<Car> carLists;

    public CarServiceImpl() {
        createListOfCars();
    }

    @Override
    public List<Car> getAllCars() {
        return this.carLists;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        return this.carLists.stream().filter(car -> car.getId() == id).findFirst();
    }

    @Override
    public List<Car> getCarsByColour(String colour) {
        return this.carLists.stream().filter(car -> car.getColour().equals(colour)).collect(Collectors.toList());
    }

    @Override
    public Boolean addCar(Car car) {
        return this.carLists.add(car);
    }

    @Override
    public void deleteCarById(long id) {
        this.carLists.remove(getCarById(id).get());
    }

    private void createListOfCars() {
        this.carLists = new ArrayList<>();
        this.carLists.add(new Car(1L, "opel", "astra", "white"));
        this.carLists.add(new Car(2L, "skoda", "kodiaq", "white"));
        this.carLists.add(new Car(3L, "skoda", "octavia", "black"));
    }
}
