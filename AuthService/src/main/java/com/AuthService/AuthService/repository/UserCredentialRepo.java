package com.AuthService.AuthService.repository;

import com.AuthService.AuthService.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepo extends JpaRepository<UserCredential,Integer> {

}
