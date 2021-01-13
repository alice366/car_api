package pl.anicieja.ksb2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anicieja.ksb2.model.car.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

   List<Car> findCarsByColour(String colour);
   List<Car> findCarsByYearBetween(long startYear, long endYear);
}
