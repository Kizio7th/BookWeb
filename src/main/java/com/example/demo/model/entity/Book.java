package com.example.demo.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book", uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "author" }) })
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @Column()
    private String author;

    @Column()
    private Float price;

    @Column()
    private Date releaseDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column()
    private String cover;

    @Column()
    private Long sold = (long) 0;

    @Column()
    private Long rating = (long) 5;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookCategory> bookCategories = new ArrayList<>();

}