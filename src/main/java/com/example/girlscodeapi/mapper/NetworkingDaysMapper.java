package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.NetworkingDaysRequest;
import com.example.girlscodeapi.model.dto.response.NetworkingDaysResponse;
import com.example.girlscodeapi.model.entity.NetworkingDays;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NetworkingDaysMapper {
    NetworkingDays mapToEntity(NetworkingDaysRequest request);
    NetworkingDaysResponse mapToResponse(NetworkingDays networkingDays);
    NetworkingDays map(NetworkingDaysRequest request, @MappingTarget NetworkingDays networkingDays);

}
