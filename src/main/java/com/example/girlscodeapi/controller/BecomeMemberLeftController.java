package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.service.becomeMember.BecomeMemberLeftService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leftBecomeMember")
@RequiredArgsConstructor
public class BecomeMemberLeftController {
    private final BecomeMemberLeftService becomeMemberLeftService;


    @PostMapping
    @Operation(summary = "this end point will post left side becomeMember", description = "post becomeMember")
    public BaseResponse<String> post(@ModelAttribute @Valid BecomeMemberLeftRequest request) {
        return BaseResponse.success(becomeMemberLeftService.post(request));
    }

}
