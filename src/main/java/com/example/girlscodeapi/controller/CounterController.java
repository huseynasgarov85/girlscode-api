package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.CounterRequest;
import com.example.girlscodeapi.model.dto.response.CounterResponse;
import com.example.girlscodeapi.model.repo.CounterRepo;
import com.example.girlscodeapi.service.counter.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterController {
    private final CounterService counterService;

    @PutMapping(path = "/{id}")
    @Operation(summary = "This api updated number of counter side also will insert number", description = "update insert api")
    public BaseResponse<String> update(@RequestBody CounterRequest counterRequest, @RequestParam(name = "id", required = false) String id) {
        return BaseResponse.success(counterService.update(counterRequest, id));
    }

    @GetMapping
    @Operation(summary = "this api get count number ", description = "getById number")
    public BaseResponse<List<CounterResponse>> getAll() {
        return BaseResponse.success(counterService.getAll());
    }
}
