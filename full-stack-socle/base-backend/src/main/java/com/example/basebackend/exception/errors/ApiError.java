package com.example.basebackend.exception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class ApiError {

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
   private LocalDateTime timestamp;
   private int status;
   private ArrayList<String> messages;
   private String description;

   public ApiError(int status, ArrayList<String> messages, String description) {
      this.timestamp = LocalDateTime.now();
      this.status = status;
      this.messages = messages;
      this.description = description;
   }
}