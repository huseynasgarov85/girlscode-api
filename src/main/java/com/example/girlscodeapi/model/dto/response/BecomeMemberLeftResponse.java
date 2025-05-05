package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
