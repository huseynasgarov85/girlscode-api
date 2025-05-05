package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialMediaInContactRequestUpdate {
    String firstUrl;
    String secondUrl;
    String thirdUrl;
    String fourthUrl;
    Integer number;
}
