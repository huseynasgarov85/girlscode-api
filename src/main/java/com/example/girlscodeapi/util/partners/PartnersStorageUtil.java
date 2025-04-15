package com.example.girlscodeapi.util.partners;

import com.example.girlscodeapi.constant.UploadPartnersFolderConstant;
import com.example.girlscodeapi.constant.UploadSliderFolderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class PartnersStorageUtil {
    public String saveFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UploadPartnersFolderConstant.UPLOAD_PARTNERS_FOLDER_PATH).resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File could not be saved: " + e.getMessage(), e);
        }
        return filePath.toString();
    }

    public void removeFileIfExists(String fileUrl) {
        try {
            System.out.println(fileUrl);
            Path filePath = Paths.get(fileUrl);
            Files.deleteIfExists(filePath);
            log.info("Deleted file: " + filePath);
        } catch (IOException e) {
            log.warn("Could not delete file: " + fileUrl + " => " + e.getMessage());
        }
    }
}
