package com.example.girlscodeapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "successStory")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuccessStory {
    @Id
    String id;
    String url;
    Integer row;
    String fullNameAZ;
    String fullNameENG;
    LocalDate dateTime;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
}
