package com.example.basebackend.exception.errors;

public class NotFoundException extends RuntimeException{
   public NotFoundException(String message) {
      super(message);
   }
}
