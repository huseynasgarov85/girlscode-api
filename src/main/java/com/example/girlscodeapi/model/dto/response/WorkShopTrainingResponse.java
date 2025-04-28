package com.example.girlscodeapi.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkShopTrainingResponse {
    @Id
    String id;
    String titleAz;
    String titleEng;
    String textAz;
    String textEng;
    String imageUrl;
}
