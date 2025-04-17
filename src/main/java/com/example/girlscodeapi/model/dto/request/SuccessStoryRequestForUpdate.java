package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuccessStoryRequestForUpdate {
    //  MultipartFile multipartFile;
    Integer row;
    String fullNameAZ;
    String fullNameENG;
    LocalDate dateTime;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
}

