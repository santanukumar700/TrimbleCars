package controller;

//import com.trimblecars.entity.Car;
//import com.trimblecars.service.CarService;
import entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        logger.info("Received request to fetch all cars");
        return carService.getAllCars();
    }

    @PostMapping
    public Car registerCar(@RequestBody Car car) {
        logger.info("Received request to register car: {}", car.getModel());
        return carService.registerCar(car);
    }
}
