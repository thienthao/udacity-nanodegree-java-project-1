package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

  @Select("SELECT * FROM files")
  List<File> findAll();

  @Select("SELECT * FROM files WHERE user_id=#{userId}")
  List<File> findAllByUserId(int userId);

  @Select("SELECT * FROM files WHERE id=#{id}")
  File findById(long id);

  @Select("SELECT * FROM files WHERE filename=#{filename}")
  File findByFilename(String filename);

  @Insert(
      "INSERT INTO files(filename, contenttype, filesize, user_id, data) "
          + " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{data})")
  int insert(File file);

  @Delete("DELETE FROM files WHERE id = #{id}")
  int delete(long id);
}
