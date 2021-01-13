package pl.anicieja.ksb2.controller.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.dao.CarDao;
import pl.anicieja.ksb2.model.car.Car;
import pl.anicieja.ksb2.service.CarService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarController {

    CarService carService;
    CarDao carDao;

    @Autowired
    public CarController(CarService carService, CarDao carDao) {
        this.carService = carService;
        this.carDao = carDao;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> allCars = carDao.findAll();
        allCars.forEach(car -> addLinkToCar(car));
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarController.class).slash(id)
                .withSelfRel();
        Car carObject = carDao.getCarById(id);
        EntityModel<Car> carEntityModel = EntityModel.of(carObject, link);

        return new ResponseEntity<>(carEntityModel, HttpStatus.OK);
    }

    @GetMapping("/colours/{colour}")
    public ResponseEntity<List<Car>> getCarsByColour(@PathVariable String colour) {
        List<Car> cars = carDao.getCarsByColour(colour);
        cars.forEach(car -> addLinkToCar(car));
        cars.forEach(car -> car.add(linkTo(CarController.class).withRel("allColours")));
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @GetMapping("/year/{yearStart}/{yearEnd}")
    public ResponseEntity<List<Car>> getCarsByYearRange(@PathVariable Long yearStart, @PathVariable Long yearEnd) {
        List<Car> cars = carDao.getCarsByYear(yearStart, yearEnd);
        cars.forEach(car -> addLinkToCar(car));
        cars.forEach(car -> car.add(linkTo(CarController.class).withRel("allYears")));
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        carDao.saveCar(car.getId(), car.getBrand(), car.getModel(), car.getColour(), car.getYear());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Car first = carDao.getCarById(newCar.getId());

        carDao.deleteCar(newCar.getId());
        carDao.saveCar(newCar.getId(), newCar.getBrand(), newCar.getModel(), newCar.getColour(), newCar.getYear());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Car first = carDao.getCarById(id);

        carDao.deleteCar(id);
        return new ResponseEntity<>(first, HttpStatus.OK);
    }

    private Car addLinkToCar(Car car) {
        return car.add(linkTo(CarController.class).slash(car.getId())
                .withSelfRel());
    }
}
