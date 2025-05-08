package com.example.girlscodeapi.service.activityBanner;

import com.example.girlscodeapi.model.dto.request.ActivityBannerRequest;
import com.example.girlscodeapi.model.dto.response.ActivityBannerResponse;

public interface ActivityBannerService {
    ActivityBannerResponse add(ActivityBannerRequest request);
    ActivityBannerResponse getById();
    ActivityBannerResponse update(ActivityBannerRequest request);
}
