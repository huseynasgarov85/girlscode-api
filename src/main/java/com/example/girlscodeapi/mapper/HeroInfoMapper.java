package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.entity.HeroInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HeroInfoMapper {
    HeroInfo mapToEntity(HeroInfoRequest heroInfoRequest);
    HeroInfoResponse mapToResponse(HeroInfo heroInfo);
    HeroInfo map(HeroInfoRequest request, @MappingTarget HeroInfo heroInfo);

}
