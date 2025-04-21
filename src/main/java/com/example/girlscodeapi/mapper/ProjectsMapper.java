package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.ProjectsRequest;
import com.example.girlscodeapi.model.dto.response.ProjectsResponse;
import com.example.girlscodeapi.model.entity.Projects;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectsMapper {
    Projects mapToEntity(ProjectsRequest request);
    ProjectsResponse mapToResponse(Projects projects);
    Projects map(ProjectsRequest request, @MappingTarget Projects projects);
}
