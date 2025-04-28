package com.example.girlscodeapi.service.dreamStart;

import com.example.girlscodeapi.model.dto.request.DreamStartRequest;
import com.example.girlscodeapi.model.dto.response.DreamStartResponse;

public interface DreamStartService {
    DreamStartResponse add(DreamStartRequest request);
    DreamStartResponse getById();
    DreamStartResponse update(DreamStartRequest request);
}
