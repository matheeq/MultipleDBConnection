package com.jwt.example.sample_JWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder().username("user1").password(passwordEncoder().encode("Password@1")).roles("ADMIN").build();
        UserDetails user = User.builder().username("atheeq").password(passwordEncoder().encode("password")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, user1);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
      return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }
}
