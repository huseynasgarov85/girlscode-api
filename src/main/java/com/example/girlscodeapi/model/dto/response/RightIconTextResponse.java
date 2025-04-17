package com.example.girlscodeapi.model.dto.response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RightIconTextResponse {
    String id;
    String iconUrl;
    String titleAz;
    String titleEng;
    String descriptionAz;
    String descriptionEng;

}
