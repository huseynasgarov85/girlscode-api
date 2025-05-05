package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.ActivityBannerRequest;
import com.example.girlscodeapi.model.dto.response.ActivityBannerResponse;
import com.example.girlscodeapi.service.activityBanner.ActivityBannerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activityBanner")
public class ActivityBannerController {
    private final ActivityBannerService service;

    @GetMapping
    @Operation(summary = "activityBanner get api",description = "this is activityBanner get api")
    public ResponseEntity<ActivityBannerResponse> getById(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById());
    }
    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "activityBanner update api",description = "this is activityBanner update api")
    public ResponseEntity<ActivityBannerResponse> update(@ModelAttribute ActivityBannerRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(request));
    }
}
