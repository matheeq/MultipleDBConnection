package com.jwt.example.sample_JWT.controllers;

import com.jwt.example.sample_JWT.models.User;
import com.jwt.example.sample_JWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;
    @RequestMapping("/user")
    public String getUser(){
        return ("User Details");
    }

    @RequestMapping("/get-allUser")
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }

    @RequestMapping("/get-session-user")
    public String getSessionUser(Principal principal){
        return principal.getName();
    }
}
