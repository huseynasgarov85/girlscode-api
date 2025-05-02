package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.SocialMediaInContactRequest;
import com.example.girlscodeapi.model.dto.response.SocialMediaInContactResponse;
import com.example.girlscodeapi.model.entity.SocialMediaInContact;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaInContactMapper {

    public SocialMediaInContact mapToEntity(SocialMediaInContactRequest socialMediaInContactRequest) {
        return SocialMediaInContact
                .builder()
                .firstUrl(socialMediaInContactRequest.getFirstUrl())
                .secondUrl(socialMediaInContactRequest.getSecondUrl())
                .thirdUrl(socialMediaInContactRequest.getThirdUrl())
                .fourthUrl(socialMediaInContactRequest.getFourthUrl())
                .build();
    }

    public SocialMediaInContactResponse mapToResponse(SocialMediaInContact socialMediaInContact) {
        return SocialMediaInContactResponse
                .builder()
                .id(socialMediaInContact.getId())
                .firstUrl(socialMediaInContact.getFirstUrl())
                .secondUrl(socialMediaInContact.getSecondUrl())
                .thirdUrl(socialMediaInContact.getThirdUrl())
                .fourthUrl(socialMediaInContact.getFourthUrl())
                .build();
    }
}
