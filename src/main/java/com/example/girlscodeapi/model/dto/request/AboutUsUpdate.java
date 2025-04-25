package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsUpdate {
    String textAZ;
    String textENG;
}
