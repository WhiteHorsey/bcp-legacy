package com.example.basebackend.service;

import com.example.basebackend.payload.request.CarRequest;
import com.example.basebackend.payload.response.CarResponse;

import java.util.List;

public interface CarService {
   List<CarResponse> getAllCars(Long studentId);

   CarResponse createCar(Long studentId, CarRequest carRequest);

   CarResponse updateCar(Long carId, CarRequest carRequest);

   String deleteCar(Long carId);
}
