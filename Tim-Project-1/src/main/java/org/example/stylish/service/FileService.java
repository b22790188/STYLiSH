package org.example.stylish.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Log4j2
public class FileService {
    private static final Path UPLOADDIR = Paths.get("uploads/");
    private static final String SERVER = "http://52.69.33.14/";

    @PostConstruct
    public void init() {
        if (!Files.exists(UPLOADDIR)) {
            try {
                Files.createDirectories(UPLOADDIR);
                log.info("Upload directory created successfully ");
            } catch (IOException e) {
                log.info("Create upload directory failed");
                throw new RuntimeException("Could not create upload directory");
            }
        } else {
            log.info("Upload directory already exists");
        }
    }

    public String uploadFile(MultipartFile file) {
        String fileUrl;
        try {
            if (!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String filename = uuid + "-" + file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                Path path = UPLOADDIR.resolve(filename);
                Files.write(path, bytes);
                return SERVER + UPLOADDIR + "/" + filename;
            } else {
                return null;
            }
        } catch (IOException e) {
            log.error("Upload file failed");
            throw new RuntimeException("Could not upload file");
        }
    }
}
