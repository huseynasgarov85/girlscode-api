package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BottomTowardFutureRequest {
    @NotNull
    String firstTitleAZ;
    @NotNull
    String firstTitleENG;
    @NotNull
    String firstValue;
    @NotNull
    String secondTitleAZ;
    @NotNull
    String secondTitleENG;
    @NotNull
    String secondValue;
    @NotNull
    String thirdTitleAZ;
    @NotNull
    String thirdTitleENG;
    @NotNull
    String thirdValue;
    @NotNull
    String fourthTitleAZ;
    @NotNull
    String fourthTitleENG;
    @NotNull
    String fourthValue;
}
