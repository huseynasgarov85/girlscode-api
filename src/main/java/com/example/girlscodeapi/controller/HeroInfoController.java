package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.repo.HeroInfoRepository;
import com.example.girlscodeapi.service.heroInfo.HeroInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping({"/heroInfo"})
public class HeroInfoController {
    private final HeroInfoService heroInfoService;
    private final HeroInfoRepository heroInfoRepository;

    @GetMapping
    public ResponseEntity<HeroInfoResponse> get() {
        return ResponseEntity.status(HttpStatus.OK).
                body(heroInfoService.get());
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "This api used update process", description = "api update photo")
    public ResponseEntity<HeroInfoResponse> update(HeroInfoRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(heroInfoService.update(request));
    }


}
