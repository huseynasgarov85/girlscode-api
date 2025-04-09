package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.service.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/slider")
@RequiredArgsConstructor
public class SliderController {
    private final SliderService sliderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String add(MultipartFile multipartFile) {
        return sliderService.add(multipartFile);
    }

}
