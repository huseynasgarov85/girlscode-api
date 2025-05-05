package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactInfoRequestUpdate {
    String firstNumber;
    String secondNumber;
    @Email
    String email;
    String location;
}
