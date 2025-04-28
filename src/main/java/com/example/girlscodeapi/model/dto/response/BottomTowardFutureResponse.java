package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BottomTowardFutureResponse {
    String id;
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
