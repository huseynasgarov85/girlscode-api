package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.FormMemberRequest;
import com.example.girlscodeapi.model.dto.request.FormMemberRequestUpdate;
import com.example.girlscodeapi.model.dto.response.FormMemberResponse;
import com.example.girlscodeapi.service.becomeMember.form.FormMemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormMemberController {
    private final FormMemberService formMemberService;

    @PostMapping
    @Operation(summary = "this end point will post user details", description = "post user details")
    public BaseResponse<Void> post(@RequestBody @Valid FormMemberRequest formMemberRequest) {
        formMemberService.post(formMemberRequest);
        return BaseResponse.success();
    }

    @GetMapping
    @Operation(summary = "this end point get all data's", description = "get all data")
    public BaseResponse<List<FormMemberResponse>> getAll() {
        return BaseResponse.success(formMemberService.getAll());
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "this end point update field's", description = "update field's")
    public BaseResponse<Void> update(@PathVariable String id, @RequestBody FormMemberRequestUpdate requestUpdate) {
        formMemberService.update(id, requestUpdate);
        return BaseResponse.success();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "remove form", description = "remove user detail")
    public BaseResponse<Void> remove(String id) {
        formMemberService.remove(id);
        return BaseResponse.success();
    }
}
