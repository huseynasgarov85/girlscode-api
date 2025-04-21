package com.example.girlscodeapi.service.projects;

import com.example.girlscodeapi.model.dto.request.ProjectsRequest;
import com.example.girlscodeapi.model.dto.response.ProjectsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProjectsService {
    ProjectsResponse add( ProjectsRequest request);
    List<ProjectsResponse> getAll();
    ProjectsResponse getById(String id);
    ProjectsResponse update(String id , ProjectsRequest request);
    void  delete(String id);

}
