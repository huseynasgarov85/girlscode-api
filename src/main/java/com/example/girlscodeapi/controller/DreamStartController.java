package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.DreamStartRequest;
import com.example.girlscodeapi.model.dto.response.DreamStartResponse;
import com.example.girlscodeapi.service.dreamStart.DreamStartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dreamStart")
public class DreamStartController {
    private final DreamStartService service;


    @GetMapping
    @Operation(summary = "DreamStart getById api ",description = "this is DreamStart getById api")
    public ResponseEntity<DreamStartResponse> getById(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById())  ;
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "DreamStart update api",description = "this is DreamStart update api")
    public ResponseEntity<DreamStartResponse> update(@ModelAttribute DreamStartRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(request));
    }


}
