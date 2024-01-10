package com.example.basebackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsResponse {
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
}
