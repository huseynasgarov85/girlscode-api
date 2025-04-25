package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.AboutUsRequest;
import com.example.girlscodeapi.model.dto.response.AboutUsResponse;
import com.example.girlscodeapi.model.entity.AboutUs;
import org.springframework.stereotype.Component;

@Component
public class AboutUsMapper {


    public AboutUs mapToEntity(AboutUsRequest aboutUsRequest, String url) {
        return AboutUs
                .builder()
                .url(url)
                .textAZ(aboutUsRequest.getTextAZ())
                .textENG(aboutUsRequest.getTextENG())
                .build();

    }

    public AboutUsResponse mapToResponse(AboutUs aboutUs) {
        return AboutUsResponse
                .builder()
                .id(aboutUs.getId())
                .textAZ(aboutUs.getTextAZ())
                .textENG(aboutUs.getTextENG())
                .url(aboutUs.getUrl())
                .build();
    }


}
