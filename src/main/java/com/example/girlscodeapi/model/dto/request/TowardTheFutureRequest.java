package com.example.girlscodeapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TowardTheFutureRequest {
    @NotNull
    @Size(max = 3, min = 3, message = "pls send us less than 4 photo")
    List<MultipartFile> multipartFile;
    @NotNull
    String textAZ;
    @NotNull
    String textENG;
    @NotNull
    String titleAZ;
    @NotNull
    String titleENG;
}
