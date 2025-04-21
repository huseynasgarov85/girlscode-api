package com.example.girlscodeapi.service.projects;

import com.example.girlscodeapi.constant.ProjectsConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ProjectsMapper;
import com.example.girlscodeapi.model.dto.request.ProjectsRequest;
import com.example.girlscodeapi.model.dto.response.ProjectsResponse;
import com.example.girlscodeapi.model.entity.Projects;
import com.example.girlscodeapi.model.repo.ProjectsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.eclipse.angus.mail.imap.OlderTerm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectsService{
    private final ProjectsMapper mapper;
    private final ProjectsRepository repository;
    @Override
    public ProjectsResponse add( ProjectsRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath= Paths.get(ProjectsConstant.ICON_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("image could not be loaded");
        }
        Projects projects=mapper.mapToEntity(request);
        projects.setImageUrl(ProjectsConstant.ICON_DIR+fileName);
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
        if (projects !=null){
            String oldFileName=Paths.get(projects.getImageUrl()).getFileName().toString();
            Path oldPath =Paths.get(ProjectsConstant.ICON_PATH).resolve(oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                System.out.println("old image could not be loaded ");
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path newPath=Paths.get(ProjectsConstant.ICON_PATH).resolve(newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            System.out.println("image could not be saved");
        }
        projects.setImageUrl(ProjectsConstant.ICON_DIR+newFileName);
        Projects projects1=mapper.map(request,projects);
        repository.save(projects1);
        return mapper.mapToResponse(projects1);
    }

    @Override
    public void delete(String id) {
    Projects projects=repository.findById(id).orElseThrow(()-> BaseException.notFound(Projects.class.getSimpleName(),"id",id));
    if (projects!=null){
        String oldFileName=Paths.get(projects.getImageUrl()).getFileName().toString();
        Path oldPath=Paths.get(ProjectsConstant.ICON_PATH).resolve(oldFileName);
        try {
            Files.deleteIfExists(oldPath);
        } catch (IOException e) {
            System.out.println("old image could not be deleted"+e.getMessage());
        }
        repository.deleteById(id);

    }
    }
}
