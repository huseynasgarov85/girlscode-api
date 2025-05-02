package com.example.girlscodeapi.service.contact;

import com.example.girlscodeapi.model.dto.request.*;
import com.example.girlscodeapi.model.dto.response.ContactInfoResponse;
import com.example.girlscodeapi.model.dto.response.ContactResponse;
import com.example.girlscodeapi.model.dto.response.SocialMediaInContactResponse;
import com.example.girlscodeapi.model.entity.SocialMediaInContact;

import java.util.List;

public interface ContactService {
    ContactResponse add(ContactRequest request);

    ContactResponse getById(String id);

    ContactResponse update(String id, ContactRequest request);

    List<ContactResponse> getAll();

    void delete(String id);

    void post(ContactInfoRequest contactInfoRequest);

    List<ContactInfoResponse> getAllContactInfo();

    void update(String id, ContactInfoRequestUpdate contactInfoRequestUpdate);

    void remove(String id);

    void postSocialMedia(SocialMediaInContactRequest socialMediaInContactRequest);

    List<SocialMediaInContactResponse> getAllSocialMedia();

    void update(SocialMediaInContactRequestUpdate requestUpdate);

}
