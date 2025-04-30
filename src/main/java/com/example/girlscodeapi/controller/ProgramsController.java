package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.ProgramsRequest;
import com.example.girlscodeapi.model.dto.response.ProgramsResponse;
import com.example.girlscodeapi.service.programs.ProgramsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programs")
public class ProgramsController {
    private final ProgramsService service;


    @GetMapping({"/{id}"})
    @Operation(summary = "programs getById api",description = "this is programs getById api")
    public ResponseEntity<ProgramsResponse> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping(value = {"/{id}"},consumes ="multipart/form-data" )
    @Operation(summary = "programs update api",description = "this is programs update api")
    public ResponseEntity<ProgramsResponse> update(@PathVariable String id,@ModelAttribute ProgramsRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }


    @GetMapping
    @Operation(summary ="programs getAll api",description = "this is programs getAll api")
    public ResponseEntity<List<ProgramsResponse>>getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

}
