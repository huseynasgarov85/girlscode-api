package com.example.girlscodeapi.util.slider;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.girlscodeapi.config.S3Config;
import com.example.girlscodeapi.constant.UploadSliderFolderConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class SliderStorageUtil {
    private final AmazonS3 amazonS3;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

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


    public String uploadToS3(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl("your-bucket-name", fileName).toString();

    }

    public void deleteFromS3(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
    }
}