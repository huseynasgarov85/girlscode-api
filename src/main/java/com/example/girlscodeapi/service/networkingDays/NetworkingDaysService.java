package com.example.girlscodeapi.service.networkingDays;

import com.example.girlscodeapi.model.dto.request.NetworkingDaysRequest;
import com.example.girlscodeapi.model.dto.response.NetworkingDaysResponse;

import java.util.List;

public interface NetworkingDaysService {
    NetworkingDaysResponse add(NetworkingDaysRequest request);
    NetworkingDaysResponse getById(String id);
    NetworkingDaysResponse update(String id,NetworkingDaysRequest request);
    void delete(String id);
    List<NetworkingDaysResponse> getAll();

}
