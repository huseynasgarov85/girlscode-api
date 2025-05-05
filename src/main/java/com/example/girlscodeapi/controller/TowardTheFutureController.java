package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequest;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BottomTowardFutureResponse;
import com.example.girlscodeapi.model.dto.response.TowardTheFutureResponse;
import com.example.girlscodeapi.service.towardTheFuture.TowardTheFutureService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/towardTheFuture")
@RequiredArgsConstructor
public class TowardTheFutureController {
    private final TowardTheFutureService towardTheFutureService;

//    @PostMapping(consumes = "multipart/form-data")
//    @Operation(summary = "this end point will post towardFuture", description = "post end point")
//    public BaseResponse<Void> post(@ModelAttribute @Valid TowardTheFutureRequest towardTheFutureRequest) {
//        towardTheFutureService.post(towardTheFutureRequest);
//        return BaseResponse.success();
//    }

    @GetMapping
    @Operation(summary = "this end point will getAll towardFuture", description = "getAll end point")
    public BaseResponse<List<TowardTheFutureResponse>> getAll() {
        return BaseResponse.success(towardTheFutureService.getAll());
    }

    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    @Operation(summary = "this end point update data's", description = "update process")
    public BaseResponse<TowardTheFutureResponse> update(
            @RequestPart(name = "request", required = false) TowardTheFutureRequestForUpdate towardTheFutureRequestForUpdate,
            @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {
        return BaseResponse.success(towardTheFutureService.update(towardTheFutureRequestForUpdate, multipartFile));
    }

//    @PostMapping
//    @Operation(summary = "this end point will post bottom side in towardFuture", description = "post bottom side")
//    public BaseResponse<Void> postBottomSide(@RequestBody BottomTowardFutureRequest request) {
//        towardTheFutureService.postBottomSide(request);
//        return BaseResponse.success();
//    }

    @GetMapping(value = "/getBottomSide")
    @Operation(summary = "this end point getAll bottom side data's", description = "getAll data's")
    public BaseResponse<List<BottomTowardFutureResponse>> getAllBottomSide() {
        return BaseResponse.success(towardTheFutureService.getAllBottomSide());
    }

    @PutMapping(value = "/{id}/updateBottomSide")
    @Operation(summary = "this end point update bottom side", description = "update bottom side")
    public BaseResponse<Void> updateBottomSide(@RequestBody BottomTowardFutureRequestForUpdate request) {
        towardTheFutureService.updateBottomSide(request);
        return BaseResponse.success();
    }
}
