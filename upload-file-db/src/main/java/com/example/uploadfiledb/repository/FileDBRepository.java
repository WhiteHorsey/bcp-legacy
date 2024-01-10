package com.example.uploadfiledb.repository;

import com.example.uploadfiledb.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}