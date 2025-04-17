package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.RightIconTextRequest;
import com.example.girlscodeapi.model.dto.response.RightIconTextResponse;

import com.example.girlscodeapi.service.rightIconText.RightIconTextService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rightIconText")
public class RightIconTextController {
    private final RightIconTextService service;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "rightIconText create api",description = "This api rightIconText created")
    public ResponseEntity<RightIconTextResponse> add(@ModelAttribute RightIconTextRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }
//    67ff91a141388d4f54b19962

    @GetMapping({"/{id}"})
    public ResponseEntity<RightIconTextResponse> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "rightIconText create api",description ="This api rightIconText updated" )
    public ResponseEntity<RightIconTextResponse> update(@RequestParam String id,@ModelAttribute RightIconTextRequest request){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "rightIconText delete api ", description = "this api rightIconText deleted")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<RightIconTextResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }
}
