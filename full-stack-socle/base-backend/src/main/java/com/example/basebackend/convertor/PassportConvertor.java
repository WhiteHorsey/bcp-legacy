package com.example.basebackend.convertor;

import com.example.basebackend.model.Passport;
import com.example.basebackend.payload.request.PassportRequest;
import com.example.basebackend.payload.response.PassportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassportConvertor {

   // CONVERTORS
   @Autowired
   private SharedConvertor sharedConvertor;

   public PassportResponse toDto(Passport passport) {
      PassportResponse passportResponse = new PassportResponse();
      passportResponse.setId(passport.getId());
      passportResponse.setNumber(passport.getNumber());
      passportResponse.setStudent(sharedConvertor.toStudentDetail(passport.getStudent()));
      return passportResponse;
   }



   public List<PassportResponse> toDtos(List<Passport> passports) {
      return passports.stream().map(this::toDto).collect(Collectors.toList());
   }

   public Passport toEntity(PassportRequest passportRequest) {
      return new Passport(passportRequest.getNumber());
   }

   public void oldToNew(Passport passport, PassportRequest passportRequest) {
      passport.setNumber(passportRequest.getNumber());
   }
}
