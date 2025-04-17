package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.SuccessStoryRequest;
import com.example.girlscodeapi.model.dto.request.SuccessStoryRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.SuccessStoryResponse;
import com.example.girlscodeapi.service.successStory.SuccessStoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/successStory")
@RequiredArgsConstructor
public class SuccessStoryController {

    private final SuccessStoryService successStoryService;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "This Api post about successStory ", description = "successStory post")
    public BaseResponse<String> post(@ModelAttribute @Valid SuccessStoryRequest successStoryRequest) {
        return BaseResponse.success(successStoryService.post(successStoryRequest));
    }

    @GetMapping
    @Operation(summary = "this api get all successStory", description = "get all successStory")
    public BaseResponse<List<SuccessStoryResponse>> getAll() {
        return BaseResponse.success(successStoryService.getAll());
    }

    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    @Operation(summary = "This end point updated", description = "This api update some fields")
    public BaseResponse<Void> update(
            @PathVariable String id,
            @RequestPart(name = "request",required = false) SuccessStoryRequestForUpdate successStoryRequest,
            @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {
        System.out.println(successStoryRequest.getFullNameAZ());
        successStoryService.update(successStoryRequest, id, multipartFile);
        return BaseResponse.success();
    }

    @DeleteMapping
    @Operation(summary = "This api removed Id in db and also folder", description = "remove successRating")
    public BaseResponse<Void> remove(@RequestParam(name = "id") String id) {
        successStoryService.remove(id);
        return BaseResponse.success();
    }
}
