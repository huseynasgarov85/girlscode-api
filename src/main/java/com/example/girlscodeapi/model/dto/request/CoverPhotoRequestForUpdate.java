package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoverPhotoRequestForUpdate {
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    LocalDate date;
}
