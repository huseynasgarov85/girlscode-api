package com.example.girlscodeapi.service;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface HeroInfoService {
    HeroInfoResponse addForTest(HeroInfoRequest heroInfoRequest);
    String add(MultipartFile multipartFile);
}
