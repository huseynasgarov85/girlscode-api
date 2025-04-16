package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.entity.Partners;
import com.example.girlscodeapi.model.response.PartnersResponse;
import org.springframework.stereotype.Component;

@Component
public class PartnersMapper {


    public PartnersResponse mapToResponse(Partners partners){
        return PartnersResponse
                .builder()
                .id(partners.getId())
                .url(partners.getUrl())
                .build();
    }
}
