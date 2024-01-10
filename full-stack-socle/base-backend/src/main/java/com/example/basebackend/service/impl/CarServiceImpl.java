package com.example.basebackend.service.impl;

import com.example.basebackend.convertor.CarConvertor;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.model.Car;
import com.example.basebackend.model.Student;
import com.example.basebackend.payload.request.CarRequest;
import com.example.basebackend.payload.response.CarResponse;
import com.example.basebackend.repository.CarRepository;
import com.example.basebackend.repository.StudentRepository;
import com.example.basebackend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

   // REPOSITORIES
   @Autowired
   private CarRepository carRepository;
   @Autowired
   private StudentRepository studentRepository;

   // CONVERTORS
   @Autowired
   private CarConvertor carConvertor;

   @Override
   public List<CarResponse> getAllCars(Long studentId) {
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student Does not exists!"));
      return carConvertor.toDtos(student.getCars());
   }

   @Override
   public CarResponse createCar(Long studentId, CarRequest carRequest) {
      Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student Does not exists!"));
      student.addCar(carConvertor.toEntity(carRequest));
      student = studentRepository.save(student);
      List<Car> cars = student.getCars();
      return carConvertor.toDto(cars.get(cars.size() - 1));
   }

   @Override
   public CarResponse updateCar(Long carId, CarRequest carRequest) {
      Car car = carRepository.findById(carId)
            .orElseThrow(() -> new NotFoundException("Car Does not exists!"));
      carConvertor.oldToNew(car, carRequest);
      return carConvertor.toDto(carRepository.save(car));
   }

   @Override
   public String deleteCar(Long carId) {
      Car car = carRepository.findById(carId)
            .orElseThrow(() -> new NotFoundException("Car Does not exists!"));
      Student student = car.getStudent();
      student.removeCar(car);
      studentRepository.save(student);
      carRepository.delete(car);
      return "Car deleted successfully !";
   }
}
