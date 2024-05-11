package com.example.demo.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.entity.BookCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;

    private String author;

    private Date releaseDate;

    private String description;

    private Long sold;

    private Long rating;

    private List<String> categories = new ArrayList<>();
}
