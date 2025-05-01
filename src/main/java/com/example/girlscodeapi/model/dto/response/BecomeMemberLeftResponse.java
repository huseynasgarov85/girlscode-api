package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BecomeMemberLeftResponse {
    String id;
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
    String url;
}
