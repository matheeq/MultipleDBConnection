package com.jwt.example.sample_JWT.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        logger.info("Header: {}", requestHeader);
        String username=null;
        String token=null;
        if(null!=requestHeader && requestHeader.startsWith("Bearer")){
            token=requestHeader.substring(7);
            try{
                username=this.jwtHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                logger.info("Illegal Argument while fetching username");
                e.printStackTrace();
            }catch (ExpiredJwtException e){
                logger.info("Given jwt token has expired");
                e.printStackTrace();
            }catch (MalformedJwtException e){
                logger.info("Some changes have done in the token");
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("Invalid Header");
        }

        if(null!=username && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken =this.jwtHelper.validateToken(token, userDetails);

            if(validateToken){
                //set the Authentication
                UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }else{
                 logger.info("Validation fails..!!");
            }

        }
        filterChain.doFilter(request, response);
    }
}
