package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.ActivityBannerRequest;
import com.example.girlscodeapi.model.dto.response.ActivityBannerResponse;
import com.example.girlscodeapi.model.entity.ActivityBanner;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActivityBannerMapper {
    ActivityBanner mapToEntity(ActivityBannerRequest request);
    ActivityBannerResponse mapToResponse(ActivityBanner activityBanner);
    ActivityBanner map(ActivityBannerRequest request, @MappingTarget ActivityBanner activityBanner);
}
