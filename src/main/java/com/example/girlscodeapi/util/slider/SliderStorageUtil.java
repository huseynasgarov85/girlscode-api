package com.example.girlscodeapi.util.slider;

import com.example.girlscodeapi.constant.StorageConstant;
import com.example.girlscodeapi.constant.UploadSliderFolderConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.model.dto.StorageDto;
import com.example.girlscodeapi.util.GCP.GCPStorageUtil;
import com.google.cloud.storage.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SliderStorageUtil {

    Storage storage;

//    public String saveFile(MultipartFile file) {
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(UploadSliderFolderConstant.UPLOAD_SLIDER_FOLDER_PATH).resolve(fileName);
//        try {
//            Files.createDirectories(filePath.getParent());
//            Files.write(filePath, file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException("File could not be saved: " + e.getMessage(), e);
//        }
//        return filePath.toString();
//    }
//
//    public void removeFileIfExists(String fileUrl) {
//        try {
//            System.out.println(fileUrl);
//            Path filePath = Paths.get(fileUrl);
//            Files.deleteIfExists(filePath);
//            log.info("Deleted file: " + filePath);
//        } catch (IOException e) {
//            log.warn("Could not delete file: " + fileUrl + " => " + e.getMessage());
//        }
//    }

    public StorageDto uploadFile(MultipartFile multipartFile) {
        log.info("photo: " + multipartFile.getOriginalFilename());
        try {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            byte[] multipartArr = multipartFile.getBytes();

            Storage storage = GCPStorageUtil.initializeStorage();

            BlobId blobId = BlobId.of(StorageConstant.bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(multipartFile.getContentType())
                    .build();

            Blob blob = storage.create(blobInfo, multipartArr);

            String staticUrl = "https://storage.googleapis.com/" + StorageConstant.bucketName + "/";
            String fullUrl = staticUrl + fileName;

            StorageDto storageDto = StorageDto.builder()
                    .url(fullUrl)
                    .fileName(blob.getBlobId().getName())
                    .build();

            log.info("Storage response: " + storageDto.getFileName() + " " + storageDto.getUrl());
            return storageDto;

        } catch (Exception e) {
            log.error("Upload error: " + e.getMessage(), e);
            throw BaseException.unexpected();
        }
    }

    public void deleteFileByUrl(String fileUrl) {
        try {
            String fileName = extractFileNameFromUrl(fileUrl);
            Storage storage = GCPStorageUtil.initializeStorage();

            BlobId blobId = BlobId.of(StorageConstant.bucketName, fileName);
            boolean deleted = storage.delete(blobId);

            if (deleted) {
                log.info("File " + fileName + " successfully deleted from Google Cloud Storage.");
            } else {
                log.warn("Failed to delete file " + fileName + " from Google Cloud Storage.");
            }
        } catch (Exception e) {
            log.error("Error deleting file from Google Cloud Storage: " + e.getMessage(), e);
        }
    }

    private static String extractFileNameFromUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String path = url.getPath();
            return path.substring(path.lastIndexOf("/") + 1);
        } catch (Exception e) {
            throw new RuntimeException("Invalid URL format", e);
        }
    }
}
