package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormMemberRequest {
    @NotNull
    String fullName;
    @NotNull
    String mobileNumber;
    @NotNull
    @Email
    String mail;
    @NotNull
    String message;
}
