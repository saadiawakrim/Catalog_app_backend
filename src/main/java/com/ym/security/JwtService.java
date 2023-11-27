package com.ym.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Autowired
    private UserDetailsService userDetailsService;

    private final static String KEY = "paint it black";
    private final static int ACCESS_TOKEN_LIFE = 30 * 60 * 1000;
    private final static int REFRESH_TOKEN_LIFE = 5 * 60 * 60 * 1000;

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(KEY);
    }

    public String generateAccessToken(UserDetails user) throws Exception{
        return  JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFE))
                .withClaim("roles", user.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()))
                .sign(getAlgorithm());
    }

    public String generateRefreshToken(UserDetails user) throws Exception{
       return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_LIFE))
                .sign(getAlgorithm());
    }

    public void sendJwtTokensResponse(String accessToken, String refreshToken, HttpServletResponse response) throws Exception{
        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType("text/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    public void sendJwtErrorResponse(Exception e, HttpServletResponse response) throws Exception{
        Map<String,String> error = new HashMap<>();
        error.put("error", e.getMessage());
        response.setContentType("text/json");
        response.setStatus(401);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    public DecodedJWT verifyToken(String token) throws Exception{
        return JWT.require(getAlgorithm()).build().verify(token);
    }

    public void setUserInContext(DecodedJWT decodedJWT) throws Exception{
        UserDetails user = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
        );
    }
}
