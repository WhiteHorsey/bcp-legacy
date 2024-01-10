package com.example.basebackend.convertor;

import com.example.basebackend.model.Car;
import com.example.basebackend.model.Passport;
import com.example.basebackend.payload.request.CarRequest;
import com.example.basebackend.payload.request.PassportRequest;
import com.example.basebackend.payload.response.CarResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConvertor {

   public CarResponse toDto(Car car) {
      CarResponse CarResponse = new CarResponse();
      CarResponse.setId(car.getId());
      CarResponse.setModel(car.getModel());
      CarResponse.setYear(car.getYear());
      return CarResponse;
   }

   public List<CarResponse> toDtos(List<Car> cars) {
      return cars.stream().map(this::toDto).collect(Collectors.toList());
   }

   public Car toEntity(CarRequest carRequest) {
      return new Car(carRequest.getModel(),carRequest.getYear());
   }

   public void oldToNew(Car oldCar, CarRequest newCar) {
      oldCar.setModel(newCar.getModel());
      oldCar.setYear(newCar.getYear());
   }
   
}
