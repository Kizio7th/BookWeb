package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.BookDto;
import com.example.demo.model.dto.EntityResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;

import lombok.AllArgsConstructor;

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

    @PostMapping("/get-book/{bookId}")
    public ResponseEntity<Object> addBook(@RequestParam Long bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return EntityResponse.content(bookDto == null ? "Book not found!!!" : null, HttpStatus.OK, bookDto);
    }

    @PostMapping("/add-book")
    public ResponseEntity<Object> addBook(@RequestPart Book book, MultipartFile cover) {
        String message = bookService.addBook(book, cover);
        return EntityResponse.content(message, HttpStatus.OK, null);
    }

    @PostMapping("/delete-book/{bookId}")
    public ResponseEntity<Object> deleteBook(@RequestParam Long bookId) {
        String message = bookService.deleteBook(bookId);
        return EntityResponse.content(message, HttpStatus.OK, null);
    }

}
