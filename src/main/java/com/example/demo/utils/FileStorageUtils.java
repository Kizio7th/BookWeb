package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageUtils {

    @Value("${file.storage}")
    private String storagePath;

    public String saveFile(String folderPath, String fileName, MultipartFile multipartFile) {
        try {
            String fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
            String file = fileName + (fileExtension != null ? "." + fileExtension : "");
            String filePath = storagePath + File.separator + folderPath + File.separator + file;
            File folder = new File(storagePath, folderPath);
            folder.mkdirs();
            Files.copy(multipartFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream getFile(String path) {
        try {
            return new FileInputStream(storagePath + File.separator + path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
