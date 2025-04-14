package com.example.girlscodeapi.model.base;


import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.exception.type.NotFoundType;
import com.example.girlscodeapi.model.enums.ResponseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import static com.example.girlscodeapi.model.enums.ErrorMessageResponse.NOT_FOUND;
import static com.example.girlscodeapi.model.enums.SuccessMessageResponse.SUCCESS;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    HttpStatus status;
    T data;
    Meta meta;


    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PROTECTED)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Meta {
        String message;
        String key;

        public static Meta of(String key, String message) {
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }

        public static Meta of(ResponseMessage responseMessage) {
            return of(responseMessage.key(), responseMessage.message());
        }

        public static Meta of(BaseException ex) {
            if (ex.getResponseMessage().equals(NOT_FOUND)) {
                NotFoundType notFoundData = ex.getNotFoundType();

                return of(
                        String.format(ex.getResponseMessage().key(), notFoundData.getTarget().toLowerCase()),
                        String.format(ex.getResponseMessage().message(), notFoundData.getTarget(), notFoundData.getField().toString())
                );
            }

            return of(ex.getResponseMessage());
        }

    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .data(data)
                .meta(Meta.of(SUCCESS))
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder()
                .meta(Meta.of(ex))
                .status(ex.getResponseMessage().status())
                .build();
    }


}
