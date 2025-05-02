package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.FormMemberRequest;
import com.example.girlscodeapi.model.dto.response.FormMemberResponse;
import com.example.girlscodeapi.model.entity.FormMember;
import org.springframework.stereotype.Component;

@Component
public class FormMemberMapper {

    public FormMember mapToEntity(FormMemberRequest formMemberRequest) {
        return FormMember
                .builder()
                .fullName(formMemberRequest.getFullName())
                .mail(formMemberRequest.getMail())
                .message(formMemberRequest.getMessage())
                .mobileNumber(formMemberRequest.getMobileNumber())
                .build();
    }

    public FormMemberResponse mapToResponse(FormMember formMember) {
        return FormMemberResponse
                .builder()
                .fullName(formMember.getFullName())
                .id(formMember.getId())
                .mail(formMember.getMail())
                .message(formMember.getMessage())
                .mobileNumber(formMember.getMobileNumber())
                .build();
    }
}
