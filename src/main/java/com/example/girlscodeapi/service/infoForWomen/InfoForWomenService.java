package com.example.girlscodeapi.service.infoForWomen;

import com.example.girlscodeapi.model.dto.request.InfoForWomenRequest;
import com.example.girlscodeapi.model.dto.response.InfoForWomenResponse;

import java.util.List;

public interface InfoForWomenService {
    InfoForWomenResponse add(InfoForWomenRequest request);
    InfoForWomenResponse getById(String id);
    InfoForWomenResponse update(String id,InfoForWomenRequest request);
    void  delete(String id);
    List<InfoForWomenResponse> getAll();
}
