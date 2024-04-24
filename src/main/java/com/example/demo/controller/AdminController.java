package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.EntityResponse;
import com.example.demo.services.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private UserService userService;

    @PostMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        return EntityResponse.content(null, HttpStatus.OK, userService.getAllUsers());
    }

}
