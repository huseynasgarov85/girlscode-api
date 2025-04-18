package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.FAQRequest;
import com.example.girlscodeapi.model.dto.response.FAQResponse;
import com.example.girlscodeapi.service.fAQ.FAQService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fAQ")
public class FAQController {
    private final FAQService fAQService;

    @PostMapping
    @Operation(summary = "FAQ create api",description = "this is FAQ create api")
    public ResponseEntity<FAQResponse> add(@RequestBody FAQRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fAQService.add(request));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<FAQResponse> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(fAQService.getById(id));

    }

    @PutMapping
    @Operation(summary = "FAQ update api",description = "this is FAQ update api")
    public ResponseEntity<FAQResponse> update(@RequestBody FAQRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(fAQService.update(request));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "FAQ delete api",description = "this is FAQ delete api")
    public ResponseEntity<Void> delete(@PathVariable String id){
        fAQService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    @Operation(summary = "FAQ getAll api",description = "this is FAQ getAll api")
    public ResponseEntity<List<FAQResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(fAQService.getAll());
    }
}
