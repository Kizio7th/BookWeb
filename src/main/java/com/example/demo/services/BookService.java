package com.example.demo.services;

import java.io.File;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.BookDto;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.utils.FileStorageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;
    private FileStorageUtils fileStorageUtils;
    private final String category = "book";

    public String addBook(Book book, MultipartFile image) {
        String cover = "default.jpg";
        if (image.getSize() != 0) {
            String originalFileName = image.getOriginalFilename();
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
            if (!"jpg".equalsIgnoreCase(fileExtension) && !"png".equalsIgnoreCase(fileExtension)) {
                return "Book's cover must be in JPG or PNG format";
            }
            fileStorageUtils.saveFile(category + File.separator + "cover", book.getTitle() + "-" + book.getAuthor(),
                    image);
        }
        book.setCover(cover);
        return "Book successfully added";
    }

    // public String modifyBook(Book book, Long id) {
    //     Book old = bookRepository.findOneById(id);
    //     Book book = new ModelMapper().map(old, Book.class);

    //     return "Book successfully modified";

    // }

    public String deleteBook(Long id) {
        Book book = bookRepository.findOneById(id);
        bookRepository.delete(book);
        return "Book successfully deleted";
    }
}
