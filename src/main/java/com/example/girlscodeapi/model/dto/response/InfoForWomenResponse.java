package com.example.girlscodeapi.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoForWomenResponse {
    String id;
    String fullNameAz;
    String fullNameEng;
    String positionAz;
    String positionEng;
    String textAz;
    String textEng;
    String imageUrl;
}
