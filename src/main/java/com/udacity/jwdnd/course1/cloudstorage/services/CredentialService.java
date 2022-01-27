package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

private final CredentialsMapper credentialsMapper;
private final EncryptionService encryptionService;

    public CredentialService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }


    public List<Credentials> displayUserCredentials(Integer userId){
        return credentialsMapper.getUserCredentials(userId);
    }


    public void addNewUserCredentials(AppForm appForm){
        Credentials credentials = new Credentials();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(appForm.getPassword(), encodedKey);
        //String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);

        credentials.setUserId(appForm.getUserId());
        credentials.setUrl(appForm.getUrl());
        credentials.setUserName(appForm.getUserName());
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        credentialsMapper.addNewUserCredentials(credentials);
    }

    public void updateUserCredentials(AppForm appForm){
        Credentials credentials = new Credentials();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(appForm.getPassword(), encodedKey);
        credentials.setCredentialId(appForm.getCredentialId());
        credentials.setUrl(appForm.getUrl());
        credentials.setUserName(appForm.getUserName());
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        credentialsMapper.updateUserCredentials(credentials);

    }

    public void deleteUserCredentials(Integer credentialId){
        credentialsMapper.deleteUserCredential(credentialId);
    }
}
