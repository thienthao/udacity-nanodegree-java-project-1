package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileTypeException;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.repository.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  // Just sample content types list for file type validation
  private final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif",
          "text/html", "application/pdf", "application/msword");
  @Autowired private FileMapper fileMapper;

  public List<File> findAll() {
    return fileMapper.findAll();
  }

  public List<File> findAllByUserId(int userId) {
    return fileMapper.findAllByUserId(userId);
  }

  public int store(MultipartFile file, int userId) throws IOException, SQLException, FileTypeException {
    File find = fileMapper.findByFilename(file.getOriginalFilename());
    if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
      throw new FileTypeException("File types not valid");
    }
    if (find != null) {
      throw new FileAlreadyExistsException("File already exists");
    }
    File f = new File();
    f.setFileName(file.getOriginalFilename());
    f.setFileSize(Long.toString(file.getSize()));
    f.setContentType(file.getContentType());
    f.setData(new SerialBlob(file.getBytes()));
    f.setUserId(userId);
    return fileMapper.insert(f);
  }

  public int delete(long id) {
    return fileMapper.delete(id);
  }

  public File find(long id) {
    return fileMapper.findById(id);
  }
}
