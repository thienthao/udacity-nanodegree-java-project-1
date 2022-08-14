package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {

  @Select("SELECT * FROM credentials")
  List<Credential> findAll();

  @Select("SELECT * FROM credentials WHERE user_id = #{userId}")
  @Result(property = "key", column = "credential_key")
  List<Credential> findAllByUserId(int userId);

  @Insert(
      "INSERT INTO credentials(url, username, credential_key, password, user_id) "
          + " VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
  int insert(Credential credential);

  @Update(
      "UPDATE credentials SET url=#{url}, username=#{username}, password=#{password} WHERE id=#{id}")
  int update(Credential credential);

  @Delete("Delete FROM credentials WHERE id=#{id}")
  int delete(long id);
}
