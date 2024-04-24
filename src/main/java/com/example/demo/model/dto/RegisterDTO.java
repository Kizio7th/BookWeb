package com.example.demo.model.dto;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String username;

    private String password;

    private String fullname;

    private String email;

    private List<String> roleList = new ArrayList<>();
}
