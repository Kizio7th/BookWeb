package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Book;



public interface BookRepository extends JpaRepository<Book, Long>{
    Book findOneById(Long id);
    List<Book> findByAuthor(String author);
    // List<Book> findByBookCategories(List<BookCategory> bookCategories);
}
