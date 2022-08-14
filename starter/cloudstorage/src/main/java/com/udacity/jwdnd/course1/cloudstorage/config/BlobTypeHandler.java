package com.udacity.jwdnd.course1.cloudstorage.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Blob.class)
public class BlobTypeHandler extends BaseTypeHandler<Blob> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Blob parameter, JdbcType jdbcType)
      throws SQLException {
    InputStream inputStream = parameter.getBinaryStream();
    try {
      preparedStatement.setBinaryStream(i, inputStream, inputStream.available());
    } catch (IOException e) {
      throw new SQLException(e);
    }
  }

  @Override
  public Blob getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
    return resultSet.getBlob(columnName);
  }

  @Override
  public Blob getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
    return resultSet.getBlob(columnIndex);
  }

  @Override
  public Blob getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
    return callableStatement.getBlob(columnIndex);
  }
}
