package com.example.basebackend.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarRequest {
   private String model;
   private String year;
}
