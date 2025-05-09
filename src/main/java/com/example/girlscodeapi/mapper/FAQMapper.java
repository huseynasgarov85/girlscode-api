package com.example.girlscodeapi.mapper;


import com.example.girlscodeapi.model.dto.request.FAQRequest;
import com.example.girlscodeapi.model.dto.response.FAQResponse;
import com.example.girlscodeapi.model.entity.FAQ;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FAQMapper {
    FAQ map(FAQRequest request);
    FAQResponse mapToResponse(FAQ fAQ);
    FAQ map(FAQRequest request, @MappingTarget FAQ faq);

}
