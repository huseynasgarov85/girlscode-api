package com.example.girlscodeapi.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactInfoResponse {
    String id;
    String firstNumber;
    String secondNumber;
    String email;
    String location;
}
