package com.example.girlscodeapi.service.aboutBanner;

import com.example.girlscodeapi.model.dto.request.AboutBannerRequest;
import com.example.girlscodeapi.model.dto.response.AboutBannerResponse;

import java.util.List;


public interface AboutBannerService {
    AboutBannerResponse add(AboutBannerRequest request);
    AboutBannerResponse getById();
    AboutBannerResponse update(AboutBannerRequest request);

}
