package com.example.basebackend.convertor;

import com.example.basebackend.model.Passport;
import com.example.basebackend.model.Student;
import com.example.basebackend.payload.response.PassportDetailsResponse;
import com.example.basebackend.payload.response.StudentDetailsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SharedConvertor {

   public StudentDetailsResponse toStudentDetail(Student student) {
      return new StudentDetailsResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail()
      );
   }

   public List<StudentDetailsResponse> toStudentDetails(List<Student> students) {
      return students.stream().map(this::toStudentDetail).collect(Collectors.toList());
   }

   public PassportDetailsResponse toPassportDetails(Passport passport) {
      return new PassportDetailsResponse(
            passport.getId(),
            passport.getNumber()
      );
   }
}
