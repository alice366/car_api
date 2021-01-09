package pl.anicieja.ksb2.controller.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.model.car.Car;
import pl.anicieja.ksb2.service.CarService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> allCars = carService.getAllCars();
        Link link = linkTo(CarController.class).withSelfRel();
        allCars.forEach(car -> addLinkToCar(car));
        CollectionModel<Car> carCollectionModel = CollectionModel.of(allCars, link);
//        return new ResponseEntity<>(carCollectionModel, HttpStatus.OK);
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarController.class).slash(id).withSelfRel();
        Optional<Car> carOptional = carService.getCarById(id);
        EntityModel<Car> carEntityModel = EntityModel.of(carOptional.get(), link);

        return carOptional.map(car -> new ResponseEntity<>(carEntityModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/colours/{colour}")
//    public ResponseEntity<CollectionModel<Car>> getCarsByColour(@PathVariable String colour) {
    public ResponseEntity<List<Car>> getCarsByColour(@PathVariable String colour) {
        List<Car> cars = carService.getCarsByColour(colour);
        cars.forEach(car -> addLinkToCar(car));
        cars.forEach(car -> car.add(linkTo(CarController.class).withRel("allColours")));
        CollectionModel<Car> carCollectionModel = CollectionModel.of(cars, linkTo(CarController.class).withSelfRel());

        if (!cars.isEmpty()) {
//            return new ResponseEntity<>(carCollectionModel, HttpStatus.OK);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        if (carService.addCar(car)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Optional<Car> first = carService.getCarById(newCar.getId());
        if (first.isPresent()){
            carService.deleteCarById(newCar.getId());
            carService.addCar(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Optional<Car> first = carService.getCarById(id);

        if (first.isPresent()){
            carService.deleteCarById(id);
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Car addLinkToCar(Car car) {
        return car.add(linkTo(CarController.class).slash(car.getId()).withSelfRel());
    }
}
