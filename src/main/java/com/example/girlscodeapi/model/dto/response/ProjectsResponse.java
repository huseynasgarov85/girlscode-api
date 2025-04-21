package com.example.girlscodeapi.model.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectsResponse {
    String id;
    String titleAz;
    String titleEng;
    String textAz;
    String textEng;
    String imageUrl;
}
