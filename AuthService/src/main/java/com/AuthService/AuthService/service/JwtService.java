package com.AuthService.AuthService.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public static final String SECRET="secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey"

            public void validateToken(final String token){
                Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            }

            public String generateToken(String userName){
                Map<String, Object> claims=new HashMap<>();
                return createToken(claims,userName);
            }

            public String createToken(Map<String, Object> claims,String userName){
                return  Jwts.builder()
                        .setClaims(claims)
                        .setSubject(userName)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
                        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
            }

            private Key getSignKey(){
                byte[] keyBytes = Decoders.BASE64.decode(SECRET);
                return Keys.hmacShaKeyFor(keyBytes);
            }
}
