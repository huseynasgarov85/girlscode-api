package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.ProjectsRequest;
import com.example.girlscodeapi.model.dto.response.ProjectsResponse;
import com.example.girlscodeapi.service.projects.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectsController {
    private final ProjectsService service;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "projects add api",description = "This is projects add api")
    public ResponseEntity<ProjectsResponse> add(@ModelAttribute ProjectsRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }
    @GetMapping
    @Operation(summary = "projects getAll api",description = "this is projects getAll api")
    public ResponseEntity<List<ProjectsResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }
    @GetMapping({"/{id}"})
    @Operation(summary = "projects get api",description = "this is projects get api")
    public ResponseEntity<ProjectsResponse> add(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping(value = {"/{id}"},consumes = "multipart/form-data")
    @Operation(summary = "projects update api",description = "this is projects update api")
    public ResponseEntity<ProjectsResponse> update(@PathVariable String id,@ModelAttribute ProjectsRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,request));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary ="projects delete api",description="this is project delete api")
    public  ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
