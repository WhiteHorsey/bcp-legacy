package com.example.basebackend.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PassportRequest {
   @Size(min = 5, max = 7)
   private String number;
}
