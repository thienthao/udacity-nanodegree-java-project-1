package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.repository.CredentialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

  @Autowired private CredentialMapper credentialMapper;

  @Autowired private EncryptionService encryptionService;

  public List<Credential> findAll() {
    return credentialMapper.findAll();
  }

  public List<Credential> findAllByUserId(int userId) {
    return credentialMapper.findAllByUserId(userId);
  }

  public int add(Credential credential) {
    SecureRandom random = new SecureRandom();
    byte[] key = new byte[16];
    random.nextBytes(key);
    String encodedKey = Base64.getEncoder().encodeToString(key);
    String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
    credential.setPassword(encryptedPassword);
    credential.setKey(encodedKey);
    return credentialMapper.insert(credential);
  }

  public int update(Credential credential) {
    String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
    credential.setPassword(encryptedPassword);
    return credentialMapper.update(credential);
  }

  public int delete(long id) {
    return credentialMapper.delete(id);
  }
}
