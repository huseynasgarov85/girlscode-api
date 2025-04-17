package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.SuccessStoryRequest;
import com.example.girlscodeapi.model.dto.response.SuccessStoryResponse;
import com.example.girlscodeapi.model.entity.SuccessStory;
import org.springframework.stereotype.Component;

@Component
public class SuccessStoryMapper {


    public SuccessStory mapToEntity(SuccessStoryRequest successStoryRequest, String url) {

        return SuccessStory
                .builder()
                .url(url)
                .row(successStoryRequest.getRow())
                .textENG(successStoryRequest.getTextENG())
                .titleAZ(successStoryRequest.getTitleAZ())
                .fullNameENG(successStoryRequest.getFullNameENG())
                .fullNameAZ(successStoryRequest.getFullNameAZ())
                .textAZ(successStoryRequest.getTextAZ())
                .titleENG(successStoryRequest.getTitleENG())
                .dateTime(successStoryRequest.getDateTime())
                .build();

    }

    public SuccessStoryResponse mapToResponse(SuccessStory successStory) {
        return SuccessStoryResponse
                .builder()
                .id(successStory.getId())
                .dateTime(successStory.getDateTime())
                .fullNameAZ(successStory.getFullNameAZ())
                .fullNameENG(successStory.getFullNameENG())
                .textAZ(successStory.getTextAZ())
                .textENG(successStory.getTextENG())
                .titleAZ(successStory.getTitleAZ())
                .titleENG(successStory.getTitleENG())
                .url(successStory.getUrl())
                .row(successStory.getRow())
                .build();
    }
}
