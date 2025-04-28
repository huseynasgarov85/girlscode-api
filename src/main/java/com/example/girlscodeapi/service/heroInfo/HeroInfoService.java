package com.example.girlscodeapi.service.heroInfo;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface HeroInfoService {

    HeroInfoResponse add(HeroInfoRequest request);
    HeroInfoResponse update(HeroInfoRequest request);
    HeroInfoResponse get();

}
