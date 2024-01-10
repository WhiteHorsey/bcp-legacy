package com.example.uploadfiledirectorydb.service;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.List;


public interface FileService {
   public void save(MultipartFile file);
   public Resource load(String filename);
   public void deleteAll();
   public List<Path> loadAll();
}
