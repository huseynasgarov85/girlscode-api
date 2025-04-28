package com.example.girlscodeapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ourValues")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OurValues {
    @Id
    String id;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
}
