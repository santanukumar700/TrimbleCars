package service;

//import com.trimblecars.entity.Car;
//import com.trimblecars.repository.CarRepository;
import entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        logger.info("Fetching all cars");
        return carRepository.findAll();
    }

    public Car registerCar(Car car) {
        logger.info("Registering new car: {}", car.getModel());
        return carRepository.save(car);
    }
}
