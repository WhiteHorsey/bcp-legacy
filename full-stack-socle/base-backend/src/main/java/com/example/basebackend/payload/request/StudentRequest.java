package com.example.basebackend.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class StudentRequest {

   @Size(min = 5, max = 15)
   private String firstName;

   @Size(min = 5, max = 15)
   private String lastName;

   @Email
   private String email;
}
