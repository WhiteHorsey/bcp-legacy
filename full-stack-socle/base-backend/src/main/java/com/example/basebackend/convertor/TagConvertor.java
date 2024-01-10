package com.example.basebackend.convertor;

import com.example.basebackend.model.Student;
import com.example.basebackend.model.Tag;
import com.example.basebackend.payload.request.TagRequest;
import com.example.basebackend.payload.response.StudentDetailsResponse;
import com.example.basebackend.payload.response.TagDetailsResponse;
import com.example.basebackend.payload.response.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConvertor {

   // CONVERTORS
   @Autowired
   private SharedConvertor sharedConvertor;

   public TagResponse toDto(Tag tag) {
      return new TagResponse(tag.getId(), tag.getName());
   }

   public List<TagResponse> toDtos(List<Tag> tags) {
      return tags.stream().map(this::toDto).collect(Collectors.toList());
   }

   public Tag toEntity(TagRequest tagRequest) {
      return new Tag(tagRequest.getName());
   }

   public void oldToNew(Tag tag, TagRequest tagRequest) {
      tag.setName(tagRequest.getName());
   }

   public TagDetailsResponse toDetailedDto(Tag tag) {
      TagDetailsResponse tagResponse = new TagDetailsResponse();
      tagResponse.setId(tag.getId());
      tagResponse.setName(tag.getName());
      tagResponse.setStudents(
            tag.getStudents() != null
                  ? sharedConvertor.toStudentDetails(tag.getStudents())
                  : null
      );
      return tagResponse;
   }

   public List<TagDetailsResponse> toDetailedDtos(List<Tag> tags) {
      return tags.stream().map(this::toDetailedDto).collect(Collectors.toList());
   }


}
