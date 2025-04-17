package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.LeftBannerRequest;
import com.example.girlscodeapi.model.dto.response.LeftBannerResponse;
import com.example.girlscodeapi.model.entity.LeftBanner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeftBannerMapper {
    LeftBanner mapToEntity(LeftBannerRequest request);
    LeftBannerResponse mapToResponse(LeftBanner leftBanner);
}
