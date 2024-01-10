package com.example.basebackend.service;


import com.example.basebackend.payload.request.TagRequest;
import com.example.basebackend.payload.response.StudentResponse;
import com.example.basebackend.payload.response.TagDetailsResponse;
import com.example.basebackend.payload.response.TagResponse;

import java.util.List;

public interface TagService {
   List<TagDetailsResponse> getAllTags();

   List<TagResponse> getAllTagsByStudentId(Long studentId);

   TagDetailsResponse getTagsById(Long tagId);

   List<StudentResponse> getAllStudentsByTagId(Long tagId);

   TagResponse addTag(Long studentId, TagRequest tagRequest);

   TagResponse updateTag(Long tagId, TagRequest tagRequest);

   String deleteTagFromStudent(Long studentId, Long tagId);

   String deleteTag(Long tagId);
}
