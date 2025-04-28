package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.WorkShopTrainingRequest;
import com.example.girlscodeapi.model.dto.response.WorkShopTrainingResponse;
import com.example.girlscodeapi.model.entity.WorkShopTraining;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkShopTrainingMapper {
    WorkShopTraining mapToEntity(WorkShopTrainingRequest request);
    WorkShopTrainingResponse mapToResponse(WorkShopTraining workshopTraining);
    WorkShopTraining map(WorkShopTrainingRequest request, @MappingTarget WorkShopTraining workshopTraining);
}
