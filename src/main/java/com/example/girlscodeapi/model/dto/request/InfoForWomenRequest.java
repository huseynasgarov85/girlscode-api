package com.example.girlscodeapi.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoForWomenRequest {
    MultipartFile file;
    String fullNameAz;
    String fullNameEng;
    String positionAz;
    String positionEng;
    String textAz;
    String textEng;

}
