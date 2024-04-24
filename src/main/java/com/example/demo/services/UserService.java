package com.example.demo.services;

import java.util.*;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.UserRole;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<UserRole> userRoles = new ArrayList<>();
        for (String roleName : Arrays.asList("ROLE_USER", "ROLE_ADMIN")) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = roleRepository.save(new Role(roleName));
            }
            userRoles.add(new UserRole(user, role));
        }
        user.setUserRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    public void addRole(User user, Role role) {
        userRoleRepository.save(new UserRole(user, role));
    }

    public List<JSONObject> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new JSONObject()
                        .put("fullname", user.getFullname())
                        .put("username", user.getUsername()))
                .collect(Collectors.toList());
    }

    public List<String> loadRoles(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());
    }

}
