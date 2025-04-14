package com.example.girlscodeapi.service;

import com.example.girlscodeapi.model.entity.Slider;
import com.example.girlscodeapi.model.response.SliderResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SliderService {
    String add( List<MultipartFile> multipartFile);

    List<SliderResponse> getAll();

    void update(String id, MultipartFile multipartFile);

    void remove(String id);

    Slider findById(String id);
}
