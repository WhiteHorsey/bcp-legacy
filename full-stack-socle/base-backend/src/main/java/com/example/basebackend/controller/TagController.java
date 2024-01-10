package com.example.basebackend.controller;


import com.example.basebackend.payload.request.TagRequest;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.payload.response.TagDetailsResponse;
import com.example.basebackend.payload.response.TagResponse;
import com.example.basebackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class TagController {

   // SERVICES
   @Autowired
   private TagService tagService;

   @GetMapping("/api/tags/")
   public List<TagDetailsResponse> getAllTags() {
      return tagService.getAllTags();
   }

   @GetMapping("/api/students/{studentId}/tags/")
   public List<TagResponse> getAllTagsByStudentId(@PathVariable(value = "studentId") Long studentId) {
      return tagService.getAllTagsByStudentId(studentId);
   }

   @GetMapping("/api/tags/{tagId}/")
   public TagDetailsResponse getTagsById(@PathVariable(value = "tagId") Long tagId) {
      return tagService.getTagsById(tagId);
   }

   @GetMapping("/api/tags/{tagId}/students/")
   public List<StudentResponse> getAllStudentsByTagId(@PathVariable(value = "tagId") Long tagId) {
      return tagService.getAllStudentsByTagId(tagId);
   }

   @PostMapping("/api/students/{studentId}/tags/")
   public TagResponse addTag(@PathVariable(value = "studentId") Long studentId, @RequestBody TagRequest tagRequest) {
      return tagService.addTag(studentId, tagRequest);
   }

   @PutMapping("/api/tags/{tagId}")
   public TagResponse updateTag(@PathVariable("tagId") Long tagId, @RequestBody TagRequest tagRequest) {
      return tagService.updateTag(tagId,tagRequest);
   }

   @DeleteMapping("/api/students/{studentId}/tags/{tagId}")
   public String deleteTagFromStudent(@PathVariable(value = "studentId") Long studentId, @PathVariable(value = "tagId") Long tagId) {
      return tagService.deleteTagFromStudent(studentId,tagId);
   }

   @DeleteMapping("/api/tags/{tagId}")
   public String deleteTag(@PathVariable("tagId") Long tagId) {
      return tagService.deleteTag(tagId);
   }

}
