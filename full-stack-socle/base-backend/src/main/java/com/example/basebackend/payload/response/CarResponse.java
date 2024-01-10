package com.example.basebackend.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarResponse {
   private Long id;
   private String model;
   private String year;
}
