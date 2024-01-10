package com.example.conflitback.exception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError {

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
   private LocalDateTime timestamp;
   private int status;
   private List<String> messages;
   private String description;

   public ApiError(int status, List<String> messages, String description) {
      this.timestamp = LocalDateTime.now();
      this.status = status;
      this.messages = messages;
      this.description = description;
   }
}