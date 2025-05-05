package com.example.girlscodeapi.service.workShopTraining;

import com.example.girlscodeapi.model.dto.request.WorkShopTrainingRequest;
import com.example.girlscodeapi.model.dto.response.WorkShopTrainingResponse;

import java.util.List;

public interface WorkShopTrainingService {
    WorkShopTrainingResponse add(WorkShopTrainingRequest request);
    WorkShopTrainingResponse getById(String id);
    WorkShopTrainingResponse update(String id,WorkShopTrainingRequest request);
    void delete(String id);
    List<WorkShopTrainingResponse> getAll();

}
