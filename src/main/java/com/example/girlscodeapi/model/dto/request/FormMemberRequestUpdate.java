package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormMemberRequestUpdate {
    String fullName;
    String message;
    String mobileNumber;
    @Email
    String mail;
}
