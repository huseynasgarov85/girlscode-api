package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BecomeMemberLeftRequest {
    String id;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    MultipartFile multipartFile;
}
