package com.example.girlscodeapi.service.leftBanner;

import com.example.girlscodeapi.model.dto.response.LeftBannerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface LeftBannerService {
    LeftBannerResponse add(MultipartFile file);
    LeftBannerResponse get();
    LeftBannerResponse update(MultipartFile file);
}
