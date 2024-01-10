package com.example.basebackend.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassportResponse {
   private Long id;
   private String number;
   private StudentDetailsResponse student;
}
