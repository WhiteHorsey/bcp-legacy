package com.example.basebackend.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TagDetailsResponse {
   private long id;
   private String name;
   private List<StudentDetailsResponse> students;
}
