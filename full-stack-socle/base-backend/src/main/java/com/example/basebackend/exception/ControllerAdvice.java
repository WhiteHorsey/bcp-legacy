package com.example.basebackend.exception;

import com.example.basebackend.exception.errors.AlreadyExistsException;
import com.example.basebackend.exception.errors.ApiError;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.exception.errors.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvice {

   @ExceptionHandler(value = {
         AlreadyExistsException.class,
         NotFoundException.class,
   })
   @ResponseStatus(NOT_FOUND)
   public ApiError CustomException(Exception ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      messages.add(ex.getMessage());
      return new ApiError(
            NOT_FOUND.value(),
            messages,
            request.getDescription(false));
   }

   @ExceptionHandler(value = TokenRefreshException.class)
   @ResponseStatus(FORBIDDEN)
   public ApiError handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      messages.add(ex.getMessage());
      return new ApiError(
            FORBIDDEN.value(),
            messages,
            request.getDescription(false));
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(BAD_REQUEST)
   public ApiError handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      ex.getBindingResult().getFieldErrors().forEach(error -> {
         messages.add(error.getDefaultMessage());
      });
      return new ApiError(
            BAD_REQUEST.value(),
            messages,
            request.getDescription(false));
   }

   @ExceptionHandler(ConstraintViolationException.class)
   @ResponseStatus(BAD_REQUEST)
   public ApiError handleConstraint(ConstraintViolationException ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      messages.add(ex.getMessage());
      return new ApiError(
            BAD_REQUEST.value(),
            messages,
            request.getDescription(false));
   }

   @ExceptionHandler(Exception.class)
   @ResponseStatus(INTERNAL_SERVER_ERROR)
   public ApiError globalExceptionHandler(Exception ex, WebRequest request) {
      ArrayList<String> messages = new ArrayList<>();
      messages.add(ex.getMessage());
      return new ApiError(
            INTERNAL_SERVER_ERROR.value(),
            messages,
            request.getDescription(false));
   }

}
