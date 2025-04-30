package com.example.girlscodeapi.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutBannerRequest {
    MultipartFile file;
    String titleAz;
    String titleEng;
    String  textAz;
    String textEng;
}
