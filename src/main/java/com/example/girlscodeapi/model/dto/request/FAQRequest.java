package com.example.girlscodeapi.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FAQRequest {
    String id;
    String titleAz;
    String titleEng;
    String textAz;
    String textEng;

}
