package com.example.basebackend.controller;

import com.example.basebackend.payload.request.StudentRequest;
import com.example.basebackend.payload.response.StudentDetailsResponse;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students/")
@CrossOrigin(value = "*")
public class StudentController {

   // SERVICES
   @Autowired
   private StudentService studentService;

   @GetMapping
   public List<StudentResponse> getAllStudents(){
      return studentService.getAllStudents();
   }

   @PostMapping
   public StudentResponse createStudent(@Valid @RequestBody StudentRequest studentRequest){
      return studentService.createStudent(studentRequest);
   }

   @GetMapping("{studentId}")
   public StudentDetailsResponse getStudent(@PathVariable Long studentId){
      return studentService.getStudent(studentId);
   }

   @PatchMapping("{studentId}")
   public StudentResponse updateStudent(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest){
      return studentService.updateStudent(studentId,studentRequest);
   }

   @DeleteMapping("{studentId}")
   public String deleteStudent(@PathVariable Long studentId){
      return studentService.deleteStudent(studentId);
   }

}
