package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialMediaInContactRequest {
    @NotNull
    String firstUrl;
    @NotNull
    String secondUrl;
    @NotNull
    String thirdUrl;
    @NotNull
    String fourthUrl;
}
