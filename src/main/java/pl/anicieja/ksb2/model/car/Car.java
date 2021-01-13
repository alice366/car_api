package pl.anicieja.ksb2.model.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends RepresentationModel<Car> {
    private long id;
    private String brand;
    private String model;
    private String colour;
    private Long year;
}
