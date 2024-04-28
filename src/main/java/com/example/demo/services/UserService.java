package com.example.demo.services;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.utils.FileStorageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private FileStorageUtils fileStorageUtils;
    private final String category = "user";

    public User saveUser(User user, MultipartFile image) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<UserRole> userRoles = new ArrayList<>();
        for (String roleName : Arrays.asList("ROLE_USER")) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = roleRepository.save(new Role(roleName));
            }
            userRoles.add(new UserRole(user, role));
        }
        String avatar = (image.getSize() == 0) ? "default.jpg"
                : fileStorageUtils.saveFile(category + File.separator + "avatar", user.getUsername(), image);
        user.setAvatar(avatar);
        user.setUserRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    public void addRole(User user, Role role) {
        userRoleRepository.save(new UserRole(user, role));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> loadRoles(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());
    }
}
