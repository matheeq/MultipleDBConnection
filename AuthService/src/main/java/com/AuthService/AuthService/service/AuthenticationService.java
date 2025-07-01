package com.AuthService.AuthService.service;

import com.AuthService.AuthService.entity.UserCredential;
import com.AuthService.AuthService.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserCredentialRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String saveUser(UserCredential user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User Added Succesfully";
    }
}
