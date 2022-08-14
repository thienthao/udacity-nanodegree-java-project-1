package com.udacity.jwdnd.course1.cloudstorage.repository;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM users WHERE username = #{username}")
  @Result(property = "id", column = "id")
  @Result(property = "username", column = "username")
  @Result(property = "salt", column = "salt")
  @Result(property = "password", column = "password")
  @Result(property = "firstName", column = "firstname")
  @Result(property = "lastName", column = "lastname")
  User findByUsername(String username);

  @Insert(
      "INSERT INTO users(username, salt, password, firstname, lastname) "
          + " VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
  int insert(User user);

  int update(User user);

  int delete(long id);
}
