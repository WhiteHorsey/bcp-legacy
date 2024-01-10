package com.example.basebackend.service.impl;

import com.example.basebackend.convertor.PassportConvertor;
import com.example.basebackend.convertor.StudentConvertor;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.model.Passport;
import com.example.basebackend.model.Student;
import com.example.basebackend.payload.request.PassportRequest;
import com.example.basebackend.payload.response.PassportResponse;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.repository.PassportRepository;
import com.example.basebackend.repository.StudentRepository;
import com.example.basebackend.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {

   // REPOSITORIES
   @Autowired
   private PassportRepository passportRepository;
   @Autowired
   private StudentRepository studentRepository;

   // CONVERTORS
   @Autowired
   private PassportConvertor passportConvertor;
   @Autowired
   private StudentConvertor studentConvertor;


   @Override
   public List<PassportResponse> getAllPassports() {
      List<Passport> passports = passportRepository.findAll();
      return passportConvertor.toDtos(passports);
   }

   @Override
   public StudentResponse createPassport(Long studentId, PassportRequest passportRequest) {
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      Passport passport = passportRepository.save(passportConvertor.toEntity(passportRequest));
      student.addPassport(passport);
      return studentConvertor.toDto(studentRepository.save(student));
   }

   @Override
   public PassportResponse updatePassport(Long passportId, PassportRequest passportRequest) {
      Passport passport = passportRepository.findById(passportId)
            .orElseThrow(() -> new NotFoundException("passport not found !"));
      passportConvertor.oldToNew(passport, passportRequest);
      return passportConvertor.toDto(passportRepository.save(passport));
   }

   @Override
   public String deletePassport(Long passportId) {
      Passport passport = passportRepository.findById(passportId)
            .orElseThrow(() -> new NotFoundException("passport not found !"));
      passport.removeStudent();
      passportRepository.save(passport);
      passportRepository.deleteById(passportId);
      return "passport deleted successfully !";
   }

   @Override
   public PassportResponse getPassport(Long studentId) {
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      Passport passport = student.getPassport();
      if (passport == null) {
         throw new NotFoundException("Passport Not Found !");
      }
      return passportConvertor.toDto(passport);
   }
}
