package pl.anicieja.ksb2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.anicieja.ksb2.model.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(long id, String brand, String model, String colour, long year) {
        Car car = new Car(id, brand, model, colour, year);
        String sql = "INSERT INTO CARS VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, car.getId(), car.getBrand(), car.getModel(), car.getColour(), car.getYear());
    }

    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM CARS";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("colour")),
                Long.parseLong(String.valueOf(element.get("year")))
        )));
        return carList;
    }

    @Override
    public List<Car> getCarsByColour(String colour) {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM CARS WHERE COLOUR = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, colour);
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("colour")),
                Long.parseLong(String.valueOf(element.get("year")))

        )));
        return carList;
    }

    @Override
    public List<Car> getCarsByYear(Long yearStart, Long yearEnd) {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM CARS WHERE YEAR BETWEEN ? AND ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, yearStart, yearEnd);
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("colour")),
                Long.parseLong(String.valueOf(element.get("year")))

        )));
        return carList;
    }

    @Override
    public Car getCarById(long id) {
        String sql = "SELECT * FROM CARS WHERE car_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Car(rs.getLong(1), rs.getString("model"), rs.getString("brand"), rs.getString("colour"), rs.getLong("year")), id);
    }

    @Override
    public void updateCar(Car newCar) {
        String sql = "UPDATE CARS SET model=?, brand=?, colour=?, year=? WHERE car_id = ?";
        jdbcTemplate.update(sql, newCar.getModel(), newCar.getBrand(), newCar.getColour(), newCar.getYear(), newCar.getId());
    }

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM CARS WHERE car_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
