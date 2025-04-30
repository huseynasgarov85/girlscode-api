package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.ProgramsRequest;
import com.example.girlscodeapi.model.dto.response.ProgramsResponse;
import com.example.girlscodeapi.model.entity.Programs;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProgramsMapper {
    Programs mapToEntity(ProgramsRequest request);
    ProgramsResponse mapToResponse(Programs programs);
    Programs  map(ProgramsRequest request, @MappingTarget Programs programs);
}
