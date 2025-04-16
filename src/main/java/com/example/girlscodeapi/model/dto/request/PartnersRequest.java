package com.example.girlscodeapi.model.dto.request;

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
public class PartnersRequest {
    @Size(max = 18, message = "you must insert max 18 photo")
    List<MultipartFile> multipartFiles;
}
