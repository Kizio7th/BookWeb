package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtToken;
import com.example.demo.data.UserDto;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.JwtUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto) {
        User user = new ModelMapper().map(userDto, User.class);
        user = userService.saveUser(user);
        return jwtUtils.generateToken(new ModelMapper().map(userDto, JwtToken.class));
    }

    @PostMapping("/login")
    public String login(@RequestBody String username, String password) {
        // User user = new ModelMapper().map(userDto, User.class);
        // userService.saveUser(user);
        return "signed";
    }
}
