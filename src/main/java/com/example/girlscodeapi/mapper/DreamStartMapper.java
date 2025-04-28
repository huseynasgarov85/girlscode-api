package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.DreamStartRequest;
import com.example.girlscodeapi.model.dto.response.DreamStartResponse;
import com.example.girlscodeapi.model.entity.DreamStart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DreamStartMapper {
    DreamStart mapToEntity(DreamStartRequest request);
    DreamStartResponse mapToResponse(DreamStart dreamStart);
    DreamStart map(DreamStartRequest request, @MappingTarget DreamStart dreamStart);
}
