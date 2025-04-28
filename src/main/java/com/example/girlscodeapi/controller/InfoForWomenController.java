package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.InfoForWomenRequest;
import com.example.girlscodeapi.model.dto.response.InfoForWomenResponse;
import com.example.girlscodeapi.service.infoForWomen.InfoForWomenService;
import io.swagger.v3.oas.annotations.Operation;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/infoForWomen")
public class InfoForWomenController {
    private final InfoForWomenService service;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "infoForWomen add api",description = "this is infoForWomen add api")
    public ResponseEntity<InfoForWomenResponse> add(@ModelAttribute InfoForWomenRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }

    @GetMapping
    @Operation(summary = "infoForWomen getAll api",description = "this is infoForWomen getAll api")
    public ResponseEntity<List<InfoForWomenResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

    @GetMapping({"/{id}"})
    @Operation(summary = "infoForWomen getById api",description = "this is infoForWomen getByID api")
    public ResponseEntity<InfoForWomenResponse> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "infoForWomen remove api",description = "this is infoForWomen remove api")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = {"/{id}"},consumes = "multipart/form-data")
    @Operation(summary = "infoForWomen update api ", description =  "this is infoForWomen update api")
    public ResponseEntity<InfoForWomenResponse> update(@PathVariable String id,@ModelAttribute InfoForWomenRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }
}
