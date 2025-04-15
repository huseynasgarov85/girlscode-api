package com.example.girlscodeapi.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class HeroInfoResponse {
    String id;
    String text;
    String url;
}
