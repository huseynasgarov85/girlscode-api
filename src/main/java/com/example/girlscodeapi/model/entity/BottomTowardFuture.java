package com.example.girlscodeapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bottomTowardFuture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BottomTowardFuture {
    @Id
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
