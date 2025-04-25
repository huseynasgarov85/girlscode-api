package com.example.girlscodeapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "towardTheFuture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TowardTheFuture {
    @Id
    String id;
    String firstPhotoUrl;
    String secondPhotoUrl;
    String thirdPhotoUrl;
    String textAZ;
    String textENG;
    String titleAZ;
    String titleENG;
}
