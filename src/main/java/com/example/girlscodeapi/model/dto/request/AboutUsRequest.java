package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsRequest {
    @NotNull
    String textAZ;
    @NotNull
    String textENG;
    @NotNull
    MultipartFile multipartFile;
}
