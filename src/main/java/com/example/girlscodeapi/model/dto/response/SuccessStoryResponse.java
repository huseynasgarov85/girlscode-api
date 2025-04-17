package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuccessStoryResponse {
    String id;
    Integer row;
    String url;
    String fullNameAZ;
    String fullNameENG;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    LocalDate dateTime;
}
