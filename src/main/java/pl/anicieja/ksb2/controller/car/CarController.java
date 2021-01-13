package pl.anicieja.ksb2.controller.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.model.car.Car;
import pl.anicieja.ksb2.repositary.CarRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarController {

    CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> allCars = carRepository.findAll();
        allCars.forEach(car -> addLinkToCar(car));
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarController.class).slash(id)
                .withSelfRel();
        Optional<Car> byId = carRepository.findById(id);
        EntityModel<Car> optionalEntityModel = EntityModel.of(byId.get(), link);

        return new ResponseEntity<>(optionalEntityModel, HttpStatus.OK);
    }

    @GetMapping("/colours/{colour}")
    public ResponseEntity<List<Car>> getCarsByColour(@PathVariable String colour) {
        List<Car> cars = carRepository.findCarsByColour(colour);
        cars.forEach(car -> addLinkToCar(car));
        cars.forEach(car -> car.add(linkTo(CarController.class).withRel("allColours")));
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @GetMapping("/year/{yearStart}/{yearEnd}")
    public ResponseEntity<List<Car>> getCarsByYearRange(@PathVariable Long yearStart, @PathVariable Long yearEnd) {
        List<Car> cars = carRepository.findCarsByYearBetween(yearStart, yearEnd);
        cars.forEach(car -> addLinkToCar(car));
        cars.forEach(car -> car.add(linkTo(CarController.class).withRel("allYears")));
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Optional<Car> first = carRepository.findById(newCar.getId());

        if (first.isPresent()) {
            carRepository.delete(newCar);
            carRepository.save(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Optional<Car> first = carRepository.findById(id);

        if (first.isPresent()) {
            carRepository.delete(first.get());
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Car addLinkToCar(Car car) {
        return car.add(linkTo(CarController.class).slash(car.getId())
                .withSelfRel());
    }
}
