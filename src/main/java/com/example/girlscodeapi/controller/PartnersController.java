package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.PartnersRequest;
import com.example.girlscodeapi.model.dto.response.PartnersResponse;
import com.example.girlscodeapi.service.partners.PartnersService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/partners")
@RequiredArgsConstructor
public class PartnersController {
    private final PartnersService partnersService;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "This end point will post partners logo", description = "logo post end point")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<String> post(@ModelAttribute @Valid PartnersRequest partnersRequest) {
        return BaseResponse.success(partnersService.post(partnersRequest));
    }

    @GetMapping
    @Operation(summary = "this end point get logos", description = "get logo api")
    public BaseResponse<List<PartnersResponse>> getAllLogos() {
        return BaseResponse.success(partnersService.getAllLogos());
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "This api updated which logo required", description = "update logo")
    public BaseResponse<Void> update(@RequestParam(name = "id") String id, @RequestParam MultipartFile multipartFile) {
        partnersService.update(id, multipartFile);
        return BaseResponse.success();
    }

    @DeleteMapping
    @Operation(summary = "this api removed one logo", description = "remove logo")
    public BaseResponse<Void> remove(@RequestParam(name = "id") String id){
        partnersService.remove(id);
        return BaseResponse.success();
    }

}
