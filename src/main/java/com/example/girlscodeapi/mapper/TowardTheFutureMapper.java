package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequest;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequest;
import com.example.girlscodeapi.model.dto.response.BottomTowardFutureResponse;
import com.example.girlscodeapi.model.dto.response.TowardTheFutureResponse;
import com.example.girlscodeapi.model.entity.BottomTowardFuture;
import com.example.girlscodeapi.model.entity.TowardTheFuture;
import org.springframework.stereotype.Component;

@Component
public class TowardTheFutureMapper {


    public TowardTheFuture mapToEntity(TowardTheFutureRequest towardTheFutureRequest) {
        return TowardTheFuture
                .builder()
                .textAZ(towardTheFutureRequest.getTextAZ())
                .textENG(towardTheFutureRequest.getTitleENG())
                .titleAZ(towardTheFutureRequest.getTitleAZ())
                .titleENG(towardTheFutureRequest.getTitleENG())
                .build();
    }

    public TowardTheFutureResponse mapToResponse(TowardTheFuture towardTheFuture) {
        return TowardTheFutureResponse
                .builder()
                .id(towardTheFuture.getId())
                .textAZ(towardTheFuture.getTextAZ())
                .textENG(towardTheFuture.getTextENG())
                .titleAZ(towardTheFuture.getTitleAZ())
                .titleENG(towardTheFuture.getTitleENG())
                .firstPhotoUrl(towardTheFuture.getFirstPhotoUrl())
                .secondPhotoUrl(towardTheFuture.getSecondPhotoUrl())
                .thirdPhotoUrl(towardTheFuture.getThirdPhotoUrl())
                .build();
    }

    public BottomTowardFuture mapToEntityForBottomSide(BottomTowardFutureRequest request) {
        return BottomTowardFuture
                .builder()
                .firstTitleAZ(request.getFirstTitleAZ())
                .firstTitleENG(request.getFirstTitleENG())
                .firstValue(request.getFirstValue())
                .secondTitleAZ(request.getSecondTitleAZ())
                .secondTitleENG(request.getSecondTitleENG())
                .secondValue(request.getSecondValue())
                .thirdTitleAZ(request.getThirdTitleAZ())
                .thirdTitleENG(request.getThirdTitleENG())
                .thirdValue(request.getThirdValue())
                .fourthTitleAZ(request.getFourthTitleAZ())
                .fourthTitleENG(request.getFourthTitleENG())
                .fourthValue(request.getFourthValue())
                .build();
    }

    public BottomTowardFutureResponse mapToResponseForBottomSide(BottomTowardFuture request) {
        return BottomTowardFutureResponse
                .builder()
                .id(request.getId())
                .firstTitleAZ(request.getFirstTitleAZ())
                .firstTitleENG(request.getFirstTitleENG())
                .firstValue(request.getFirstValue())
                .secondTitleAZ(request.getSecondTitleAZ())
                .secondTitleENG(request.getSecondTitleENG())
                .secondValue(request.getSecondValue())
                .thirdTitleAZ(request.getThirdTitleAZ())
                .thirdTitleENG(request.getThirdTitleENG())
                .thirdValue(request.getThirdValue())
                .fourthTitleAZ(request.getFourthTitleAZ())
                .fourthTitleENG(request.getFourthTitleENG())
                .fourthValue(request.getFourthValue())
                .build();
    }
}
