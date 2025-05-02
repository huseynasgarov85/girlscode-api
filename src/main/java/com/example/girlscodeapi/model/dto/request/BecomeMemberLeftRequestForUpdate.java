package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BecomeMemberLeftRequestForUpdate {
    String titleAZ;
    String titleENG;
    String textAZ;
    String textENG;
}
