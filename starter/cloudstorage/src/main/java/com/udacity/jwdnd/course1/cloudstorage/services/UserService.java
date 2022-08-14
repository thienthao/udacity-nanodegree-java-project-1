package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;

    private static User current;

    public static User getCurrent() {
        return current;
    }

    public static void setCurrent(User current) {
        UserService.current = current;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public int signup(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        user.setPassword(hashService.getHashedValue(user.getPassword(), encodedSalt));
        user.setSalt(encodedSalt);
        return userMapper.insert(user);
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.findByUsername(username) == null;
    }
}
