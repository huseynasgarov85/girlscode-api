package com.example.girlscodeapi.service.heroInfo;

import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface HeroInfoService {

    HeroInfoResponse add(MultipartFile file,String text);
    HeroInfoResponse update(MultipartFile file,String text);
    HeroInfoResponse get();

}
