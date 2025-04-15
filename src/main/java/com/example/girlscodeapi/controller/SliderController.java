package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.response.SliderResponse;
import com.example.girlscodeapi.service.slider.SliderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/slider")
@RequiredArgsConstructor
public class SliderController {
    private final SliderService sliderService;

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "slider photo api", description = "this end point add to slider photo")
    public BaseResponse<String> add(@RequestParam("photo") List<MultipartFile> multipartFile) {
        return BaseResponse.success(sliderService.add(multipartFile));
    }


    @GetMapping
    @Operation(summary = "this end point getAll photos to slider side ", description = "getAll photo to slider")
    public BaseResponse<List<SliderResponse>> getAll() {
        return BaseResponse.success(sliderService.getAll());
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "This api used update process", description = "api update photo")
    public BaseResponse<Void> update(@RequestParam(name = "id") String id, MultipartFile multipartFile) {
        sliderService.update(id, multipartFile);
        return BaseResponse.success();
    }

    @DeleteMapping
    @Operation(summary = "This api removed photo", description = "photo removed process")
    public BaseResponse<Void> removePhotoById(@RequestParam(name = "id") String id) {
        sliderService.remove(id);
        return BaseResponse.success();
    }


}
