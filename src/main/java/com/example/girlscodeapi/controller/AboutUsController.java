package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.AboutUsUpdate;
import com.example.girlscodeapi.model.dto.response.AboutUsResponse;
import com.example.girlscodeapi.service.aboutUs.AboutUsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/aboutUs")
@RequiredArgsConstructor
public class AboutUsController {
    private final AboutUsService aboutUsService;

//    @PostMapping(consumes = "multipart/form-data")
//    @Operation(summary = "this end point posted one time photo and text", description = "post photo and foto")
//    public BaseResponse<AboutUsResponse> post(@ModelAttribute @Valid AboutUsRequest aboutUsRequest) {
//        return BaseResponse.success(aboutUsService.post(aboutUsRequest));
//    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "this end point will update text and photo", description = "update about us text and photo")
    public BaseResponse<Void> update(
            @RequestPart(name = "request", required = false) AboutUsUpdate aboutUsUpdate,
            @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {
        aboutUsService.update(aboutUsUpdate, multipartFile);
        return BaseResponse.success();
    }

    @GetMapping
    @Operation(summary = "this end point get all data's about us", description = "get all")
    public BaseResponse<List<AboutUsResponse>> getAll() {
        return BaseResponse.success(aboutUsService.getAll());
    }

}
