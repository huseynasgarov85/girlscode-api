package com.example.girlscodeapi.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FAQResponse {
    String id;
    String titleAz;
    String titleEng;
    String textAz;
    String textEng;
}
