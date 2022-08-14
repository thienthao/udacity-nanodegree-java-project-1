package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {

  @Select("SELECT * FROM NOTES")
  List<Note> findAll();

  @Select("SELECT * FROM NOTES WHERE user_id = #{userId}")
  @Result(property = "id", column = "id")
  @Result(property = "title", column = "title")
  @Result(property = "description", column = "description")
  @Result(property = "userId", column = "user_id")
  List<Note> findAllByUserId(int userId);

  @Insert(
      "INSERT INTO NOTES(title, description, user_id) "
          + " VALUES (#{title}, #{description}, #{userId})")
  int insert(Note note);

  @Update("UPDATE notes SET title=#{title}, description=#{description} WHERE id=#{id}")
  int update(Note note);

  @Delete("DELETE FROM notes WHERE id = #{id}")
  int delete(long id);
}
