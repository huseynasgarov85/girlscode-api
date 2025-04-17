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
public class SuccessStoryRequest {
    @NotNull
    MultipartFile multipartFile;
    @NotNull
    Integer row;
    @NotNull
    String fullNameAZ;
    @NotNull
    String fullNameENG;
    @NotNull
    LocalDate dateTime;
    @NotNull
    String titleAZ;
    @NotNull
    String titleENG;
    @NotNull
    String textAZ;
    @NotNull
    String textENG;
}
