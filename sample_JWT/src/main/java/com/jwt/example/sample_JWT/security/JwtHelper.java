package com.jwt.example.sample_JWT.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY= 5*60*60;

    private String secret="secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey";

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    // For retrieving any information from token we need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if token has Expired
    private Boolean isTokenExpired(String token){
        final Date expiration= getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate Token for User
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims= new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating Token
    //1. Define claims of the token, like Issuer , Expiration , Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret Key
    //3. According to JWS compact serialization (https://tools.ietf.org/html/....)
    //   compacting of the JWT to a URL-safe string
    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //Validate Token
    public  Boolean validateToken(String token, UserDetails userDetails){
        final String username= getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&  !isTokenExpired(token));
    }
}
