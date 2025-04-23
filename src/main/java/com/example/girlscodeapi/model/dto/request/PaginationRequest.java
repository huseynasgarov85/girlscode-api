package com.example.girlscodeapi.model.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationRequest {
    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

}
