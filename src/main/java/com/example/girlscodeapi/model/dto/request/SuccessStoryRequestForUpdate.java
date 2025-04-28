package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuccessStoryRequestForUpdate {
    MultipartFile multipartFile;
    Integer row;
    String fullNameAZ;
    String fullNameENG;
    LocalDate dateTime;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
}

