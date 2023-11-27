package com.ym.controller;

import com.ym.dto.AuthDto;
import com.ym.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDto authDto) throws Exception{
        System.out.println("auth");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", jwtService.generateAccessToken((UserDetails) authentication.getPrincipal()));
        tokens.put("refresh_token", jwtService.generateRefreshToken((UserDetails) authentication.getPrincipal()));
        return tokens;
    }
}
