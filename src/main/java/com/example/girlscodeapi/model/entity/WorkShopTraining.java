package com.example.girlscodeapi.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workshop_training")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkShopTraining {
    String id;
    String titleAz;
    String TitleEng;
    String textAz;
    String textEng;
    String imageUrl;

}
