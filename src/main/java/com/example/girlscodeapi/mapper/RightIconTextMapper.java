package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.RightIconTextRequest;
import com.example.girlscodeapi.model.dto.response.RightIconTextResponse;
import com.example.girlscodeapi.model.entity.RightIconText;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  RightIconTextMapper {
    RightIconText mapToEntity(RightIconTextRequest request);
    RightIconTextResponse mapToResponse(RightIconText rightIconText);
}
