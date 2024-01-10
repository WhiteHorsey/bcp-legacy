package com.example.basebackend.service.impl;

import com.example.basebackend.convertor.StudentConvertor;
import com.example.basebackend.convertor.TagConvertor;
import com.example.basebackend.exception.errors.NotFoundException;
import com.example.basebackend.model.Student;
import com.example.basebackend.model.Tag;
import com.example.basebackend.payload.request.TagRequest;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.payload.response.TagDetailsResponse;
import com.example.basebackend.payload.response.TagResponse;
import com.example.basebackend.repository.StudentRepository;
import com.example.basebackend.repository.TagRepository;
import com.example.basebackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

   // REPOSITORIES
   @Autowired
   private StudentRepository studentRepository;
   @Autowired
   private TagRepository tagRepository;

   // CONVERTORS
   @Autowired
   private StudentConvertor studentConvertor;
   @Autowired
   private TagConvertor tagConvertor;

   @Override
   public List<TagDetailsResponse> getAllTags() {
      // GET ALL AND FORMAT
      return tagConvertor.toDetailedDtos(tagRepository.findAll());
   }

   @Override
   public List<TagResponse> getAllTagsByStudentId(Long studentId) {
      // IF STUDENT NOT EXISTS => ERROR
      if (!studentRepository.existsById(studentId)) {
         throw new NotFoundException("Not found student with id = " + studentId);
      }
      // FORMAT
      return tagConvertor.toDtos(tagRepository.findTagsByStudentsId(studentId));
   }

   @Override
   public TagDetailsResponse getTagsById(Long tagId) {
      // GET TAG
      Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new NotFoundException("Tag with Id : " + tagId + " Not found !"));
      // GET STUDENTS, FORMAT
      return tagConvertor.toDetailedDto(tag);
   }

   @Override
   public List<StudentResponse> getAllStudentsByTagId(Long tagId) {
      // GET TAG
      Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new NotFoundException("Tag with Id : " + tagId + " Not found !"));
      // GET STUDENTS, FORMAT
      return studentConvertor.toDtos(studentRepository.findStudentByTagsId(tag.getId()));
   }

   @Override
   public TagResponse addTag(Long studentId, TagRequest tagRequest) {
      // GET STUDENT
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      Tag tag;
      if (tagRepository.existsByName(tagRequest.getName())) {
         // RETRIEVE TAG IF EXISTS
         tag = tagRepository.findByName(tagRequest.getName());
      } else {
         // CREATE TAG IF NOT EXISTS
         tag = tagRepository.save(tagConvertor.toEntity(tagRequest));
      }
      // LINK STUDENT AND TAG, SAVE
      student.addTag(tag);
      studentRepository.save(student);
      // FORMAT
      return tagConvertor.toDto(tag);
   }

   @Override
   public TagResponse updateTag(Long tagId, TagRequest tagRequest) {
      // GET TAG
      Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new NotFoundException("Tag with Id : " + tagId + " Not found !"));
      // UPDATE
      tagConvertor.oldToNew(tag, tagRequest);
      // SAVE AND FORMAT
      return tagConvertor.toDto(tagRepository.save(tag));
   }

   @Override
   public String deleteTagFromStudent(Long studentId, Long tagId) {
      // GET STUDENT
      Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotFoundException("Student not found !"));
      // BREAK THE ASSOCIATION
      student.removeTag(tagId);
      studentRepository.save(student);
      return "Removed Successfully tag : " + tagId + " from student : " + student.getId();
   }

   @Override
   public String deleteTag(Long tagId) {
      // GET TAG
      Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new NotFoundException("Tag with Id : " + tagId + " Not found !"));
      // BREAK THE ASSOCIATIONS BETWEEN TAG AND STUDENTS
      tag.getStudents().forEach(student -> {
         student.removeTag(tagId);
         studentRepository.save(student);
      });
      // DELETE TAG
      tagRepository.deleteById(tagId);
      return "tag deleted successfully !";
   }
}
