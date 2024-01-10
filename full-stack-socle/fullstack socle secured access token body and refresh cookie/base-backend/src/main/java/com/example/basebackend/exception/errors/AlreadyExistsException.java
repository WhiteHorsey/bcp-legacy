package com.example.basebackend.exception.errors;

public class AlreadyExistsException extends RuntimeException{
   public AlreadyExistsException(String message) {
      super(message);
   }
}
