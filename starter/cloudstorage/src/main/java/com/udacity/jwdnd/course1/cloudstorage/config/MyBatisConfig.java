package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

  @Bean
  public BlobTypeHandler blobTypeHandler() throws Exception {
    return new BlobTypeHandler();
  }
}
