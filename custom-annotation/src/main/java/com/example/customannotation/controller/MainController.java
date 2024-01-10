package com.example.customannotation.controller;

import com.example.customannotation.model.Employee;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Validated
@RestController
public class MainController {

   @PostMapping
   public void test(@RequestBody @Valid Employee employee){
      System.out.println(employee.toString());
   }
}
