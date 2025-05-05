package com.example.girlscodeapi.service.projects;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ProjectsMapper;
import com.example.girlscodeapi.model.dto.request.ProjectsRequest;
import com.example.girlscodeapi.model.dto.response.ProjectsResponse;
import com.example.girlscodeapi.model.entity.Projects;
import com.example.girlscodeapi.model.repo.ProjectsRepository;
import com.example.girlscodeapi.util.projects.ProjectsUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectsService{
    private final ProjectsMapper mapper;
    private final ProjectsRepository repository;
    private final ProjectsUtil projectsUtil;
    @Override
    public ProjectsResponse add( ProjectsRequest request) {
        Projects projects=mapper.mapToEntity(request);
        projects.setImageUrl(projectsUtil.saveFile(request.getFile()));
        repository.save(projects);
        return mapper.mapToResponse(projects);
    }

    @Override
    public List<ProjectsResponse> getAll() {
        List<Projects> projectsList=repository.findAll();
        return projectsList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectsResponse getById(String id) {
        Projects projects=repository.findById(id).orElseThrow(()->
                BaseException.notFound(Projects.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(projects);
    }

    @Override
    public ProjectsResponse update(String id, ProjectsRequest request) {
        Projects projects=repository.findById(id).orElseThrow(()-> BaseException.notFound(Projects.class.getSimpleName(),"id",id));
        if (projects.getImageUrl() !=null){
           projectsUtil.removeFile(projects.getImageUrl());
        }
        Projects projects1=mapper.map(request,projects);
        projects1.setImageUrl(projectsUtil.saveFile(request.getFile()));
        repository.save(projects1);
        return mapper.mapToResponse(projects1);
    }

    @Override
    public void delete(String id) {
    Projects projects=repository.findById(id).orElseThrow(()-> BaseException.notFound(Projects.class.getSimpleName(),"id",id));
    if (projects.getImageUrl()!=null){
       projectsUtil.removeFile(projects.getImageUrl());
        repository.deleteById(id);
    }
    }
}
