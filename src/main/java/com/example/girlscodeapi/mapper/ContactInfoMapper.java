package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.ContactInfoRequest;
import com.example.girlscodeapi.model.dto.response.ContactInfoResponse;
import com.example.girlscodeapi.model.entity.ContactInfo;
import org.springframework.stereotype.Component;

@Component
public class ContactInfoMapper {

    public ContactInfo mapToEntity(ContactInfoRequest contactInfoRequest) {
        return ContactInfo
                .builder()
                .email(contactInfoRequest.getEmail())
                .firstNumber(contactInfoRequest.getFirstNumber())
                .secondNumber(contactInfoRequest.getSecondNumber())
                .location(contactInfoRequest.getLocation())
                .build();
    }

    public ContactInfoResponse mapToResponse(ContactInfo contactInfo){
        return ContactInfoResponse
                .builder()
                .id(contactInfo.getId())
                .email(contactInfo.getEmail())
                .firstNumber(contactInfo.getFirstNumber())
                .location(contactInfo.getLocation())
                .secondNumber(contactInfo.getSecondNumber())
                .build();

    }
}
