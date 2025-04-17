package com.example.girlscodeapi.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RightIconTextRequest {
    MultipartFile multipartFile;
    String titleAz;
    String titleEng;
    String descriptionAz;
    String descriptionEng;
}
