package com.example.demo.model.dto;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    private Float price;

    private Long sold;

    private Long rating;

    private InputStream cover = null;

    private List<String> categories = new ArrayList<>();
}
