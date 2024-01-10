package com.example.basebackend.repository;

import com.example.basebackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
   boolean existsByEmail(String email);
   List<Student> findStudentByTagsId(Long tagId);
}
