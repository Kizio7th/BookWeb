package com.example.demo.config;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    private Long uId;
    private String username;
    private String fullname;
    private List<String> roles;

    List<GrantedAuthority> getAuthorities() {
        if (roles == null)
            return new ArrayList<>();
        return roles.stream().map(s -> (GrantedAuthority) () -> s).toList();
    }
}
