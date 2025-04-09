package com.example.girlscodeapi.exception.type;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
public class NotFoundType {

    String target;
    Map<String, String> field;

    public static NotFoundType of(String target, Map<String, String> field) {
        return NotFoundType
                .builder()
                .field(field)
                .target(target)
                .build();
    }


}
