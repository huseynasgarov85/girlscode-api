package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OurValuesRequest {
    @NotNull
    String textAZ;
    @NotNull
    String textENG;
    @NotNull
    String titleAZ;
    @NotNull
    String titleENG;
}
