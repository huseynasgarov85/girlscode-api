package com.example.girlscodeapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "coverPhoto")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoverPhoto {
    @Id
    String id;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    LocalDate date;
    String url;
    @DBRef
    List<Photo> photos;
}
