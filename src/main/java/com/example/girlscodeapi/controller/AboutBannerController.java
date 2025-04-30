package com.example.girlscodeapi.controller;


import com.example.girlscodeapi.model.dto.request.AboutBannerRequest;
import com.example.girlscodeapi.model.dto.response.AboutBannerResponse;
import com.example.girlscodeapi.service.aboutBanner.AboutBannerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/aboutBanner")
public class AboutBannerController {
    private final AboutBannerService service;

    @GetMapping
    @Operation(summary = "aboutBanner getById api",description = "this is aboutBanner getById api ")
    public ResponseEntity<AboutBannerResponse> getById(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById());
    }
    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "aboutBanner update api",description = "this is aboutBanner update api")
    public ResponseEntity<AboutBannerResponse> update( @ModelAttribute AboutBannerRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(request));
    }



}
