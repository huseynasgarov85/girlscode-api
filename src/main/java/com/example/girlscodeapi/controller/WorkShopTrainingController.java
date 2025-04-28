package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.WorkShopTrainingRequest;
import com.example.girlscodeapi.model.dto.response.WorkShopTrainingResponse;
import com.example.girlscodeapi.service.WorkShopTraining.WorkShopTrainingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workShopTraining")
public class WorkShopTrainingController {
    private final WorkShopTrainingService service;
    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "WorkShopTraining insert api",description = "this is WorkShopTraining insert api")
    public ResponseEntity<WorkShopTrainingResponse> add(@ModelAttribute WorkShopTrainingRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }
    @GetMapping({"/{id}"})
    @Operation(summary = "WorkShopTraining getById api",description = "this is WorkShopTraining getById api")
    public ResponseEntity<WorkShopTrainingResponse> getById(@PathVariable String id){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping(value = {"/{id}"},consumes = "multipart/form-data")
    @Operation(summary = "WorkShopTraining update api",description = "this is WorkShopTraining update api")
    public ResponseEntity<WorkShopTrainingResponse> update(@PathVariable String id,@ModelAttribute WorkShopTrainingRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,request));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "WorkShopTraining remove api ",description = "this is WorkShopTraining remove api")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    @Operation(summary = "WorkShopTraining getAll api",description = "this is WorkShopTraining getAll api")
    public ResponseEntity<List<WorkShopTrainingResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

}
