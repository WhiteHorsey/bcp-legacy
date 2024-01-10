package com.example.controller;

import com.example.model.db2.Car;
import com.example.repository.db1.DriverRepository;
import com.example.repository.db2.CarRepository;
import com.example.model.db1.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @PostMapping
    public void create(){
        carRepository.save(new Car("BMW"));
        driverRepository.save(new Driver("Adnane"));
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @GetMapping("/drivers")
    public List<Driver> getDrivers(){
        return driverRepository.findAll();
    }
}
