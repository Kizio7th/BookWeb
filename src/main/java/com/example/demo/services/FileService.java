// package com.example.demo.services;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// @Service
// public class FileService {

//     @Value("${file.storage}")
//     private String path;

//     public String uploadFile(MultipartFile multipartFile) {
//         try {
//             String fileName = multipartFile.getOriginalFilename();
//             String filePath = path + File.separator + fileName;
//             File file = new File(path);
//             if (!file.exists())
//                 file.mkdirs();
//             Files.copy(multipartFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//             return fileName;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     public InputStream getResourceFile(String fileName) {
//         try {
//             String filePath = path + File.separator + fileName;
//             return new FileInputStream(filePath);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }
// }
