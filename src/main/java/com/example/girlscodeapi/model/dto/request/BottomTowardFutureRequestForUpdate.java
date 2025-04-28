package com.example.girlscodeapi.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BottomTowardFutureRequestForUpdate {
    String firstTitleAZ;
    String firstTitleENG;
    String firstValue;
    String secondTitleAZ;
    String secondTitleENG;
    String secondValue;
    String thirdTitleAZ;
    String thirdTitleENG;
    String thirdValue;
    String fourthTitleAZ;
    String fourthTitleENG;
    String fourthValue;
}
