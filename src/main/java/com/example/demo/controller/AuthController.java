package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.model.dto.EntityResponse;
import com.example.demo.model.dto.JWTAuthResponse;
import com.example.demo.model.dto.LoginDto;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.entity.User;
import com.example.demo.services.UserService;
import com.example.demo.utils.JwtUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/public")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO userDto) {
        User user = new ModelMapper().map(userDto, User.class);
        user = userService.saveUser(user);
        return EntityResponse.content("Registered", HttpStatus.OK, null);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return EntityResponse.content("Authentication", HttpStatus.OK, jwtAuthResponse);
    }
}
