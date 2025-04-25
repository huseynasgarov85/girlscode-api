package com.example.girlscodeapi.service.towardTheFuture;

import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequest;
import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequest;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BottomTowardFutureResponse;
import com.example.girlscodeapi.model.dto.response.TowardTheFutureResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TowardTheFutureService {
    void post(TowardTheFutureRequest towardTheFutureRequest);

    List<TowardTheFutureResponse> getAll();

    TowardTheFutureResponse update(TowardTheFutureRequestForUpdate request, MultipartFile multipartFile);

    void postBottomSide(BottomTowardFutureRequest request);

    List<BottomTowardFutureResponse> getAllBottomSide();

    void updateBottomSide(BottomTowardFutureRequestForUpdate request);

}
