package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.AboutBannerRequest;
import com.example.girlscodeapi.model.dto.response.AboutBannerResponse;
import com.example.girlscodeapi.model.entity.AboutBanner;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AboutBannerMapper {
    AboutBanner mapToEntity(AboutBannerRequest request);
    AboutBannerResponse mapToResponse(AboutBanner aboutBanner);
    AboutBanner map(AboutBannerRequest request, @MappingTarget AboutBanner aboutBanner);
}
