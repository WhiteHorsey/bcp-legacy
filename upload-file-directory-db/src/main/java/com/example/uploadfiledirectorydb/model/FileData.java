package com.example.uploadfiledirectorydb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
   private String filename;
   private String url;
   private Long size;
}
