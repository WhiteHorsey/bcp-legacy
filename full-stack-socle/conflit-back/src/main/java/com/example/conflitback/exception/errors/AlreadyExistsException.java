package com.example.conflitback.exception.errors;

public class AlreadyExistsException extends RuntimeException{
   public AlreadyExistsException(String message) {
      super(message);
   }
}
