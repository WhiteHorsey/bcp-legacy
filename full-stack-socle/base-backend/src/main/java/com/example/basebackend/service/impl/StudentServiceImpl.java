package com.example.basebackend.service.impl;

import com.example.basebackend.convertor.SharedConvertor;
import com.example.basebackend.convertor.StudentConvertor;
import com.example.basebackend.exception.errors.AlreadyExistsException;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.model.Student;
import com.example.basebackend.payload.request.StudentRequest;
import com.example.basebackend.payload.response.StudentDetailsResponse;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.repository.StudentRepository;
import com.example.basebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

   // REPOSITORIES
   @Autowired
   private StudentRepository studentRepository;

   // CONVERTORS
   @Autowired
   private StudentConvertor studentConvertor;
   @Autowired
   private SharedConvertor sharedConvertor;

   @Override
   public List<StudentResponse> getAllStudents() {
      List<Student> students = studentRepository.findAll();
      return studentConvertor.toDtos(students);
   }

   @Override
   public StudentResponse createStudent(StudentRequest studentRequest) {
      if (studentRepository.existsByEmail(studentRequest.getEmail())) {
         throw new AlreadyExistsException("Student already exists !");
      }
      return studentConvertor.toDto(studentRepository.save(studentConvertor.toEntity(studentRequest)));
   }

   @Override
   public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      studentConvertor.oldToNew(student,studentRequest);
      return studentConvertor.toDto(studentRepository.save(student));
   }

   @Override
   public String deleteStudent(Long studentId) {
      if (!studentRepository.existsById(studentId)) {
         throw new NotFoundException("Student not found !");
      }
      studentRepository.deleteById(studentId);
      return "Student deleted successfully !";
   }

   @Override
   public StudentDetailsResponse getStudent(Long studentId) {
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      return sharedConvertor.toStudentDetail(student);
   }
}
