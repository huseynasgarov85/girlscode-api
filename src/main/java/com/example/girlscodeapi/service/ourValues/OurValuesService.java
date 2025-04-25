package com.example.girlscodeapi.service.ourValues;

import com.example.girlscodeapi.model.dto.request.OurValuesRequest;
import com.example.girlscodeapi.model.dto.response.OurValuesResponse;

import java.util.List;

public interface OurValuesService {

    void post(OurValuesRequest ourValuesRequest);

    List<OurValuesResponse> getAll();

    void update(String id, OurValuesRequest ourValuesRequest);

    void remove(String id);
}
