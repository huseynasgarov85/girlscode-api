package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.response.CounterResponse;
import com.example.girlscodeapi.model.entity.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterMapper {

    public CounterResponse mapToResponse(Counter counter) {
        return CounterResponse
                .builder()
                .id(counter.getId())
                .number(counter.getNumber())
                .build();
    }
}
