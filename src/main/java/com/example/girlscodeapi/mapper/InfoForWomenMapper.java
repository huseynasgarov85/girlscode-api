package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.InfoForWomenRequest;
import com.example.girlscodeapi.model.dto.response.InfoForWomenResponse;
import com.example.girlscodeapi.model.entity.InfoForWomen;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InfoForWomenMapper {
    InfoForWomen mapToEntity(InfoForWomenRequest request);
    InfoForWomenResponse mapToResponse(InfoForWomen infoForWomen);
    InfoForWomen  map(InfoForWomenRequest request, @MappingTarget InfoForWomen women);
}
