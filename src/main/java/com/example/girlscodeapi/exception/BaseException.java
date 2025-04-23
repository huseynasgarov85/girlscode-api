package com.example.girlscodeapi.exception;

import com.example.girlscodeapi.exception.type.NotFoundType;
import com.example.girlscodeapi.model.enums.exception.ResponseMessage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static com.example.girlscodeapi.model.enums.exception.ErrorMessageResponse.NOT_FOUND;
import static com.example.girlscodeapi.model.enums.exception.ErrorMessageResponse.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {
    ResponseMessage responseMessage;
    NotFoundType notFoundType;

    @Override
    public String getMessage() {
        return responseMessage.message();
    }


    public static BaseException unexpected() {
        return BaseException.of(UNEXPECTED);
    }

    public static BaseException of(ResponseMessage responseMessage) {
        return BaseException.builder().responseMessage(responseMessage).build();
    }

    public static BaseException notFound(String target, String field, String value) {
        return BaseException
                .builder()
                .responseMessage(NOT_FOUND)
                .notFoundType(
                        NotFoundType.of(target, Map.of(field, value))
                )
                .build();
    }

}
