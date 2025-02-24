package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String userName);

    User findOneById(Long id);

}
