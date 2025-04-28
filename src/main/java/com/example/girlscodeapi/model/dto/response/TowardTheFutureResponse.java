package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TowardTheFutureResponse {
    String id;
    String firstPhotoUrl;
    String secondPhotoUrl;
    String thirdPhotoUrl;
    String textAZ;
    String textENG;
    String titleAZ;
    String titleENG;
}
