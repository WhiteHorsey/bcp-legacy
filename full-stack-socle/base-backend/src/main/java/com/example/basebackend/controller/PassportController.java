package com.example.basebackend.controller;

import com.example.basebackend.payload.request.PassportRequest;
import com.example.basebackend.payload.response.PassportResponse;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class PassportController {

   // SERVICES
   @Autowired
   private PassportService passportService;

   @GetMapping("/api/passports/")
   public List<PassportResponse> getAllPassports() {
      return passportService.getAllPassports();
   }

   @GetMapping("/api/students/{studentId}/passport/")
   public PassportResponse getPassport(@PathVariable Long studentId) {
      return passportService.getPassport(studentId);
   }

   @PostMapping("/api/students/{studentId}/passports/")
   public StudentResponse createPassport(@PathVariable Long studentId, @Valid @RequestBody PassportRequest passportRequest) {
      return passportService.createPassport(studentId, passportRequest);
   }

   @PatchMapping("/api/passports/{passportId}/")
   public PassportResponse updatePassport(@PathVariable Long passportId, @Valid @RequestBody PassportRequest passportRequest) {
      return passportService.updatePassport(passportId, passportRequest);
   }

   @DeleteMapping("/api/passports/{passportId}/")
   public String deletePassport(@PathVariable Long passportId) {
      return passportService.deletePassport(passportId);
   }

}
