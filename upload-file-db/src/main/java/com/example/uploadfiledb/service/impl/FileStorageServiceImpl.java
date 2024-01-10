package com.example.uploadfiledb.service.impl;

import com.example.uploadfiledb.model.FileDB;
import com.example.uploadfiledb.repository.FileDBRepository;
import com.example.uploadfiledb.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;


@Service
public class FileStorageServiceImpl implements FileStorageService {

   @Autowired
   private FileDBRepository fileDBRepository;

   @Override
   public FileDB store(MultipartFile file) throws IOException {
      String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      FileDB fileDb = new FileDB(fileName, file.getContentType(), file.getBytes());
      return fileDBRepository.save(fileDb);
   }

   @Override
   public FileDB getFile(String id) {
      return fileDBRepository.findById(id).get();
   }

   @Override
   public Stream<FileDB> getAllFiles() {
      return fileDBRepository.findAll().stream();
   }
}
