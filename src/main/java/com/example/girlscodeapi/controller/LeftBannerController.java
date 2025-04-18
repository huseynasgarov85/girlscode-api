package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.response.LeftBannerResponse;
import com.example.girlscodeapi.service.leftBanner.LeftBannerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leftBanner")
public class LeftBannerController {
    private final LeftBannerService service;

    @GetMapping
    public ResponseEntity<LeftBannerResponse> get() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.get());
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "leftBanner update api", description = "this end point update to leftBanner")
    public ResponseEntity<LeftBannerResponse> update(MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(file));
    }


}
