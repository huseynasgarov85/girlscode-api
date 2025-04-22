package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.ContactRequest;
import com.example.girlscodeapi.model.dto.response.ContactResponse;
import com.example.girlscodeapi.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact mapToEntity(ContactRequest request);
    ContactResponse mapToResponse(Contact contact);
    Contact map(ContactRequest request, @MappingTarget Contact contact);
}
