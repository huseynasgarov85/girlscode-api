package com.example.girlscodeapi.service.counter;

import com.example.girlscodeapi.model.dto.request.CounterRequest;
import com.example.girlscodeapi.model.dto.response.CounterResponse;

import java.util.List;

public interface CounterService {

    String update(CounterRequest counterRequest);

    List<CounterResponse> getAll();

    void postOneTime(CounterRequest counterRequest);
}
