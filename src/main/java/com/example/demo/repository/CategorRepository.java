package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Category;

public interface CategorRepository extends JpaRepository<Category, Long> {
    
}
