package com.example.girlscodeapi.model.dto.response;

import com.example.girlscodeapi.model.entity.Photo;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsResponse {
    String id;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    LocalDate date;
    String url;
    List<Photo> photos;
    Recommended recommended;

}
