package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OurValuesResponse {
    String id;
    String textAZ;
    String textENG;
    String titleAZ;
    String titleENG;
}
