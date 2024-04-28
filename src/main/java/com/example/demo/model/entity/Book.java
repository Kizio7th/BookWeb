package com.example.demo.model.entity;
import java.sql.Date;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column()
    private String title;
    
    @Column()
    private String author;
    
    @Column()
    private Date releaseDate;
    
    @Column()
    private String pageNumber ;
    
    @Column()
    private Long sold;
	
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<BookCategory> bookCategories = new ArrayList<>();
    
}