package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.OurValuesRequest;
import com.example.girlscodeapi.model.dto.response.OurValuesResponse;
import com.example.girlscodeapi.model.entity.OurValues;
import org.springframework.stereotype.Component;

@Component
public class OurValuesMapper {


    public OurValues mapToEntity(OurValuesRequest ourValuesRequest) {
        return OurValues
                .builder()
                .textENG(ourValuesRequest.getTextENG())
                .textAZ(ourValuesRequest.getTextAZ())
                .titleAZ(ourValuesRequest.getTitleAZ())
                .titleENG(ourValuesRequest.getTitleENG())
                .build();

    }

    public OurValuesResponse mapToResponseForGetAll(OurValues ourValues) {
        return OurValuesResponse
                .builder()
                .textENG(ourValues.getTextENG())
                .titleAZ(ourValues.getTitleAZ())
                .titleENG(ourValues.getTitleENG())
                .textAZ(ourValues.getTextAZ())
                .id(ourValues.getId())
                .build();
    }

    public OurValues mapFromRequestToEntityForUpdate(OurValuesRequest ourValuesRequest, OurValues ourValues) {
        ourValues.setTitleAZ(ourValuesRequest.getTitleAZ());
        ourValues.setTitleENG(ourValuesRequest.getTitleENG());
        ourValues.setTextAZ(ourValuesRequest.getTextAZ());
        ourValues.setTextENG(ourValuesRequest.getTextENG());
        return ourValues;
    }

}
