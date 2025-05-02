package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BecomeMemberLeftResponse;
import com.example.girlscodeapi.service.becomeMember.left.BecomeMemberLeftService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/leftBecomeMember")
@RequiredArgsConstructor
public class BecomeMemberLeftController {
    private final BecomeMemberLeftService becomeMemberLeftService;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "this end point will post left side becomeMember", description = "post becomeMember")
    public BaseResponse<String> post(@ModelAttribute @Valid BecomeMemberLeftRequest request) {
        return BaseResponse.success(becomeMemberLeftService.post(request));
    }

    @GetMapping
    @Operation(summary = "this end point will get becomeMembers data's", description = "getAll datas")
    public BaseResponse<List<BecomeMemberLeftResponse>> getAll() {
        return BaseResponse.success(becomeMemberLeftService.getAll());
    }

    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    @Operation(summary = "this end point will update which one you want to update", description = "update field's")
    public BaseResponse<Void> update(
                                     @RequestPart(name = "request", required = false) BecomeMemberLeftRequestForUpdate request,
                                     @RequestPart(name = "photo", required = false) MultipartFile multipartFile
    ) {
        becomeMemberLeftService.update( request, multipartFile);
        return BaseResponse.success();
    }


}
