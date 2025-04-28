package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TowardTheFutureRequestForUpdate {
    String textAZ;
    String textENG;
    String titleAZ;
    String titleENG;
    @Min(1)
    @Max(3)
    @NotNull
    Integer photoNumber;
}
