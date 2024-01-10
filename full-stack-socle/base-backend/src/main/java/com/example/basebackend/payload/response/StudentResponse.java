package com.example.basebackend.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentResponse {
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private PassportDetailsResponse passport;
   private List<CarResponse> cars;
   private List<TagResponse> tags;
}
