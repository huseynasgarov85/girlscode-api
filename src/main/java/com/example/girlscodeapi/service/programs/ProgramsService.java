package com.example.girlscodeapi.service.programs;

import com.example.girlscodeapi.model.dto.request.ProgramsRequest;
import com.example.girlscodeapi.model.dto.response.ProgramsResponse;

import java.util.List;

public interface ProgramsService {
    ProgramsResponse add(ProgramsRequest request);
    ProgramsResponse getById(String id);
    ProgramsResponse update(String id,ProgramsRequest  request);
    void  delete(String id);
    List<ProgramsResponse> getAll();
}
