package com.jwt.example.sample_JWT.services;

import com.jwt.example.sample_JWT.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

   private List<User> store= new ArrayList<>();

   public UserService(){
       store.add(new User(1,"Alpha", "Alpha@gmail"));
       store.add(new User(2,"Beta", "Beta@gmail"));
       store.add(new User(3,"Gama", "Gama@gmail"));
   }


   public List<User> getAllUsers(){
       return store;
   }
}
