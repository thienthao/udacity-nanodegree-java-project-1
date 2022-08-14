package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
  private int id;
  private String fileName;
  private String contentType;
  private String fileSize;
  private int userId;
  private Blob data;
}
