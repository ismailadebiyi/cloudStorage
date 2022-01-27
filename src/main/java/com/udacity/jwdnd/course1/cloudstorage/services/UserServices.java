package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServices {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserServices(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

//    public String encodedSalt(){
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        return Base64.getEncoder().encodeToString(salt);
//    }
//
//    public String harshedPassword(String plainPassword){
//        return hashService.getHashedValue(plainPassword, encodedSalt());
//    }

    public int createUser(Users user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new Users(null, user.getUserName(), encodedSalt,
                hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public Boolean isUsernameAvailable(String userName){
        return userMapper.getUser(userName) == null;
    }

    public Users getUser(String username){
        return userMapper.getUser(username);
    }
}
