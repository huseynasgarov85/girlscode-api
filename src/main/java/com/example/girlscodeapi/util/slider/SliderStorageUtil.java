package com.example.girlscodeapi.util.slider;

import com.example.girlscodeapi.constant.UploadSliderFolderConstant;
import com.example.girlscodeapi.model.repo.InputFileRepository;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SliderStorageUtil {

   // Storage storage;
    private final InputFileRepository fileRepository;

    public String saveFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UploadSliderFolderConstant.UPLOAD_SLIDER_FOLDER_PATH).resolve(fileName);
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


//    public StorageDto uploadFile(MultipartFile multipartFile) {
//        try {
//            String fileName = multipartFile.getName() + "_" + System.currentTimeMillis();
//            byte[] multipartArr = multipartFile.getBytes();
//            BlobId blobId = BlobId.of(StorageConstant.bucketName, fileName);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
//            String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
//            log.info("blob :" + blobInfo);
//            Blob blob = storageConfig.create(blobInfo, multipartArr);
//            StorageDto storageDto = StorageDto.builder().url(fileUrl).fileName(blob.getBlobId().getName()).build();
//            log.info("storage response " + " " + storageDto.getFileName() + " " + storageDto.getUrl());
//            return storageDto;
//        } catch (IOException e) {
//            throw new RuntimeException("Cloud upload failed", e);
//        }
//    }

//    public StorageDto uploadFile(MultipartFile multipartFile) {
//        try {
//            String originalFilename = multipartFile.getOriginalFilename();
//            String extension = originalFilename != null && originalFilename.contains(".")
//                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
//                    : ".jpg";
//            String fileName = UUID.randomUUID() + extension;
//
//            BlobId blobId = BlobId.of(bucketName, fileName);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
//                    .setContentType(multipartFile.getContentType())
//                    .build();
//
//            try (InputStream inputStream = multipartFile.getInputStream()) {
//                Blob blob = storage.create(blobInfo, inputStream);
//                String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
//
//                log.info("File uploaded: {}", fileUrl);
//
//                return StorageDto.builder()
//                        .url(fileUrl)
//                        .fileName(blob.getName())
//                        .build();
//            }
//
//        } catch (IOException e) {
//            log.error("Upload failed", e);
//            throw new RuntimeException("Cloud upload failed", e);
//        }
//    }


//    public InputFile uploadFile(MultipartFile file) {
//        String originalFileName = Objects.requireNonNull(file.getOriginalFilename(), "File name is null");
//
//        try {
//            String contentType = Files.probeContentType(new File(originalFileName).toPath());
//            StorageDto fileDto = uploadFile(file, originalFileName, contentType);
//
//            InputFile inputFile = InputFile.builder()
//                    .fileName(fileDto.getFileName())
//                    .fileUrl(fileDto.getUrl())
//                    .build();
//            return fileRepository.save(inputFile);
//        } catch (IOException e) {
//            throw new RuntimeException("File upload failed", e);
//        }
//    }

}
