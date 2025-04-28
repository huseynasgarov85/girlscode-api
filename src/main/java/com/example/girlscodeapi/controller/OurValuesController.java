package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.OurValuesRequest;
import com.example.girlscodeapi.model.dto.response.OurValuesResponse;
import com.example.girlscodeapi.service.ourValues.OurValuesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ourValues")
@RequiredArgsConstructor
public class OurValuesController {
    private final OurValuesService ourValuesService;

    @PostMapping
    @Operation(summary = "this end point will add our values", description = "post our values")
    public BaseResponse<Void> post(@RequestBody @Valid OurValuesRequest ourValuesRequest) {
        ourValuesService.post(ourValuesRequest);
        return BaseResponse.success();
    }

    @GetMapping
    @Operation(summary = "This end point get all data's", description = "get all data's")
    public BaseResponse<List<OurValuesResponse>> getAll() {
        return BaseResponse.success(ourValuesService.getAll());
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "this end point update values", description = "update data's")
    public BaseResponse<Void> update(@PathVariable String id, OurValuesRequest ourValuesRequest) {
        ourValuesService.update(id, ourValuesRequest);
        return BaseResponse.success();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "this end point remove values from db", description = "remove data's")
    public BaseResponse<Void> remove(@PathVariable String id) {
        ourValuesService.remove(id);
        return BaseResponse.success();
    }
}
