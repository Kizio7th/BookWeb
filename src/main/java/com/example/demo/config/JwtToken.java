package com.example.demo.config;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    private Long uId;
    private String username;
    private String fullname;
    private List<String> roles;
}
