package com.example.demo.services;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.entity.UserRole;
import com.example.demo.model.dto.EntityResponse;
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

    public String saveUser(User user, MultipartFile image) {
        String avatar = "default.jpg";
        if (image.getSize() != 0) {
            String originalFileName = image.getOriginalFilename();
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
            if (!"jpg".equalsIgnoreCase(fileExtension) && !"png".equalsIgnoreCase(fileExtension)) {
                return "Avatar must be in JPG or PNG format";
            }
            fileStorageUtils.saveFile(category + File.separator + "avatar", user.getUsername(), image);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<UserRole> userRoles = new ArrayList<>();
        for (String roleName : Arrays.asList("ROLE_USER")) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = roleRepository.save(new Role(roleName));
            }
            userRoles.add(new UserRole(user, role));
        }
        user.setAvatar(avatar);
        user.setUserRoles(userRoles);
        userRepository.save(user);
        return "Registered";
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
