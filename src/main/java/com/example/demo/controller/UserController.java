// package com.example.demo.controller;

// import java.io.IOException;
// import java.io.InputStream;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.model.dto.EntityResponse;
// import com.example.demo.services.FileService;
// import com.example.demo.services.UserService;

// import jakarta.servlet.http.HttpServletResponse;
// import lombok.AllArgsConstructor;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.util.StreamUtils;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// @RestController
// @RequestMapping("/api/user")
// @AllArgsConstructor
// public class UserController {

//     private FileService fileService;

//     @PostMapping("file/upload")
//     public ResponseEntity<Object> uploadFile(@RequestPart MultipartFile multipartFile) {
//         String uploadFileName = fileService.uploadFile(multipartFile);
//         return EntityResponse.content("File uploaded: " + uploadFileName, HttpStatus.OK, null);
//     }

//     @PostMapping("/{fileName}")
//     public ResponseEntity<Object> getFile(@PathVariable String fileName, HttpServletResponse response) {
//         InputStream resourceFile = fileService.getResourceFile(fileName);
//         response.setContentType(MediaType.IMAGE_PNG_VALUE);
//         try {
//             StreamUtils.copy(resourceFile, response.getOutputStream());
//         } catch (IOException e) {
//             e.printStackTrace();
//             return EntityResponse.content("Failed to get file", HttpStatus.INTERNAL_SERVER_ERROR, null);
//         }

//         return EntityResponse.content("File retrieved successfully", HttpStatus.OK, null);

//     }

// }
