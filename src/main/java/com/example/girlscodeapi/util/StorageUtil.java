package com.example.girlscodeapi.util;

import com.example.girlscodeapi.constant.UploadFolderConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.model.dto.StorageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
@Slf4j
public class StorageUtil {
    public StorageDto getStorageDto(MultipartFile multipartFile) {
        log.info("photo: " + multipartFile.getOriginalFilename());
        try {
            String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

            Path folderPath = Paths.get(UploadFolderConstant.UPLOAD_FOLDER_PATH);

            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            Path filePath = folderPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = filePath.toUri().toString();

            log.info("Saved file to local path: " + fileUrl);

            return StorageDto.builder()
                    .url(fileUrl)
                    .build();

        } catch (IOException e) {
            log.error("Error occurred while saving file", e);
            throw BaseException.unexpected();
        }
    }

}
