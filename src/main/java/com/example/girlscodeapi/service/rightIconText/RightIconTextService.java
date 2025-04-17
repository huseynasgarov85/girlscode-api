package com.example.girlscodeapi.service.rightIconText;

import com.example.girlscodeapi.model.dto.request.RightIconTextRequest;
import com.example.girlscodeapi.model.dto.response.RightIconTextResponse;

import java.util.List;

public interface RightIconTextService {
    RightIconTextResponse add(RightIconTextRequest request);

    RightIconTextResponse getById(String id);
    RightIconTextResponse update(String id,RightIconTextRequest request);
    void delete(String id);

    List<RightIconTextResponse> getAll();
}