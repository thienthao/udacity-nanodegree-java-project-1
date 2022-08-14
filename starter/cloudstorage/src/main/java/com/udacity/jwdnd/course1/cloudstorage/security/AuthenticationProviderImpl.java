package com.udacity.jwdnd.course1.cloudstorage.security;

import java.util.ArrayList;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.repository.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

  @Autowired
  private HashService hashService;

  @Autowired
  private UserMapper userMapper;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String username = auth.getName();
    String password = auth.getCredentials().toString();

    User user = userMapper.findByUsername(username);
    if (user != null) {
      String salt = user.getSalt();
      String hashedPassword = hashService.getHashedValue(password, salt);
      if (user.getPassword().equals(hashedPassword)) {
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
      }
    }

    return null;
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }

}
