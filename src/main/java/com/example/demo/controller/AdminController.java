package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.EntityResponse;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private UserService userService;
    private BookService bookService;

    @PostMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        return EntityResponse.content(null, HttpStatus.OK, userService.getAllUsers());
    }

    @PostMapping("/add-book")
    public ResponseEntity<Object> addBook(@RequestPart Book book, MultipartFile cover) {
        String message = bookService.addBook(book, cover);
        return EntityResponse.content(message, HttpStatus.OK, null);
    }

}
