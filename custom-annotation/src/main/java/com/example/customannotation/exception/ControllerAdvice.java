package com.example.customannotation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ApiError handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      ex.getAllErrors().forEach(error -> {
         messages.add(error.getDefaultMessage());
      });
      return new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            messages,
            request.getDescription(false));
   }

   @ExceptionHandler(ConstraintViolationException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ApiError handleConstraint(ConstraintViolationException ex, WebRequest request) {
      System.out.println("ConstraintViolationException");
      ArrayList<String> messages = new ArrayList<>();
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
         messages.add(violation.getMessage());
      }
      return new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            messages,
            request.getDescription(false));
   }


   @ExceptionHandler(Exception.class)
   public ApiError handleConstraint(Exception ex, WebRequest request) {
      System.out.println("Exception");
      ArrayList<String> messages = new ArrayList<>();
      messages.add(ex.getMessage());
      return new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            messages,
            request.getDescription(false));
   }
}
