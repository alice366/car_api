package pl.anicieja.ksb2.controller.car;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.model.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> carLists;

    public CarApi() {
        this.carLists = new ArrayList<>();
        carLists.add(new Car(1L, "opel", "astra", "white"));
        carLists.add(new Car(2L, "skoda", "kodiaq", "white"));
        carLists.add(new Car(3L, "skoda", "octavia", "black"));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carLists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carLists.stream().filter(car -> car.getId() == id).findFirst();
        return first.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/colours")
    public ResponseEntity<List<Car>> getCarsByColour(@RequestParam String colour) {
        List<Car> cars = carLists.stream().filter(car -> car.getColour().equals(colour)).collect(Collectors.toList());
        if (!cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        if (carLists.add(car)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modifyCar(@RequestBody Car newCar) {
        Optional<Car> first = carLists.stream().filter(car -> car.getId() == newCar.getId()).findFirst();

        if (first.isPresent()){
            carLists.remove(first.get());
            carLists.add(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Optional<Car> first = carLists.stream().filter(car -> car.getId() == id).findFirst();

        if (first.isPresent()){
            carLists.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
