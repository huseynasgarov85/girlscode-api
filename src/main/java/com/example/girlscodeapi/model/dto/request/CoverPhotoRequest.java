package com.example.girlscodeapi.model.dto.request;

import com.example.girlscodeapi.model.enums.recommended.Recommended;
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
public class CoverPhotoRequest {
    @NotNull
    MultipartFile multipartFile;
    @NotNull
    String titleAZ;
    @NotNull
    String titleENG;
    @NotNull
    String textAZ;
    @NotNull
    String textENG;
    @NotNull
    LocalDate date;
    @NotNull
    Recommended recommended;
}
