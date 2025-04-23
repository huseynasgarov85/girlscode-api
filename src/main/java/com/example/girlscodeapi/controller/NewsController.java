package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.*;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.NewsResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.enums.filter.DateFilter;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import com.example.girlscodeapi.service.news.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping(value = "/coverPhoto", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This end point added coverPhoto", description = "this end point will post coverPhoto and other fields")
    public BaseResponse<CoverPhotoResponse> post(@ModelAttribute @Valid CoverPhotoRequest coverPhoto) {
        return BaseResponse.success(newsService.post(coverPhoto));
    }

    @PostMapping(value = "/listPhoto", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This end point will post inside of coverPhoto", description = "post ListPhotos")
    public BaseResponse<PhotoResponse> postListPhoto(@ModelAttribute @Valid PhotoRequest photoRequest) {
        return BaseResponse.success(newsService.postListPhoto(photoRequest));
    }
//    /new?recommended=true
//    /new
//    /new?page1&data=10

    @GetMapping
    @Operation(summary = "This end point get news , pagination , recommended true will return", description = "get news")
    public BaseResponse<?> getAll(
            @RequestParam(required = false) DateFilter dateFilter,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Recommended recommended
    ) {
        Object getAll = newsService.getAll(dateFilter, page, size, recommended);
        final PaginationRequest request = new PaginationRequest(page, size);
        return BaseResponse.success(getAll);
    }

    @PutMapping(path = "/{coverId}", consumes = "multipart/form-data")
    @Operation(summary = "this end point updated coverPhoto", description = "update if fields appropriate")
    public BaseResponse<String> update(
            @PathVariable String coverId,
            @RequestPart(name = "request", required = false) CoverPhotoRequestForUpdate coverPhotoRequestForUpdate,
            @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {

        return BaseResponse.success(newsService.updateCover(coverId, coverPhotoRequestForUpdate, multipartFile));
    }

    @PutMapping(value = "/ListPhotoUpdate/{photoId}", consumes = "multipart/form-data")
    @Operation(summary = "This api updated CoverPhoto inside List photos", description = "updated List photos")
    public BaseResponse<String> updateListPhotos(
            @PathVariable String photoId,
            @RequestPart(name = "request", required = false) PhotoRequestForUpdate photoRequestForUpdate,
            @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {
        return BaseResponse.success(newsService.updateListPhotos(photoId, photoRequestForUpdate, multipartFile));
    }

    @DeleteMapping(path = "/{coverId}")
    @Operation(summary = "this api removed coverId and also List photos removed", description = "this api removed all data's")
    public BaseResponse<Void> remove(@PathVariable String coverId) {
        newsService.removed(coverId);
        return BaseResponse.success();
    }
}
