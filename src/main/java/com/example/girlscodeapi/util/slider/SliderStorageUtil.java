package com.example.girlscodeapi.util.slider;

import com.example.girlscodeapi.constant.StorageConstant;
import com.example.girlscodeapi.constant.UploadSliderFolderConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.model.dto.StorageDto;
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
        log.info("photo " + multipartFile.getName());
        try {
            String fileName = multipartFile.getName() + "_" + System.currentTimeMillis();
            byte[] multipartArr = multipartFile.getBytes();
            BlobId blobId = BlobId.of(StorageConstant.bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
            Blob blob = storage.create(blobInfo, multipartArr);
            log.info("Blob :" + storage);
            String staticUrl = "https://storage.cloud.google.com/" + StorageConstant.bucketName + "/";
            String fullUrl = staticUrl + fileName;
            StorageDto storageDto = StorageDto.builder()
                    .url(fullUrl)
                    .fileName(blob.getBlobId().getName())
                    .build();
            log.info("storage response " + " " + storageDto.getFileName() + " " + storageDto.getUrl());
            return storageDto;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR " + e.getMessage());
            throw BaseException.unexpected();
        }
    }


    public void deleteFileByUrl(String fileUrl) {
        try {
            String fileName = extractFileNameFromUrl(fileUrl);

            Storage storage = StorageOptions.getDefaultInstance().getService();

            BlobId blobId = BlobId.of("girlscode-test", fileName);
            Blob blob = storage.get(blobId);

            if (blob != null) {
                boolean deleted = blob.delete();
                if (deleted) {
                    System.out.println("File " + fileName + " successfully deleted from Google Cloud Storage.");
                } else {
                    System.err.println("Failed to delete file " + fileName + " from Google Cloud Storage.");
                }
            } else {
                System.err.println("File " + fileName + " not found in Google Cloud Storage.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error deleting file from Google Cloud Storage: " + e.getMessage());
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
