package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialMediaInContactResponse {
    String id;
    String firstUrl;
    String secondUrl;
    String thirdUrl;
    String fourthUrl;
}
