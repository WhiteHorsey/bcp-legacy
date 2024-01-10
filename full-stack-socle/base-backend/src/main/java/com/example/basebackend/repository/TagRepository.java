package com.example.basebackend.repository;

import com.example.basebackend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
   List<Tag> findTagsByStudentsId(Long studentId);

   Tag findByName(String name);

   boolean existsByName(String name);
}