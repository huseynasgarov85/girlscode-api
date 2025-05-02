package com.example.girlscodeapi.service.contact;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ContactInfoMapper;
import com.example.girlscodeapi.mapper.ContactMapper;
import com.example.girlscodeapi.model.dto.request.ContactInfoRequest;
import com.example.girlscodeapi.model.dto.request.ContactInfoRequestUpdate;
import com.example.girlscodeapi.model.dto.request.ContactRequest;
import com.example.girlscodeapi.model.dto.response.ContactInfoResponse;
import com.example.girlscodeapi.model.dto.response.ContactResponse;
import com.example.girlscodeapi.model.entity.Contact;
import com.example.girlscodeapi.model.entity.ContactInfo;
import com.example.girlscodeapi.model.repo.ContactInfoRepo;
import com.example.girlscodeapi.model.repo.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final ContactMapper mapper;
    private final ContactRepository repository;
    private final ContactInfoRepo contactInfoRepo;
    private final ContactInfoMapper contactInfoMapper;

    @Override
    public ContactResponse add(ContactRequest request) {
        Contact contact = mapper.mapToEntity(request);
        repository.save(contact);
        return mapper.mapToResponse(contact);
    }

    @Override
    public ContactResponse getById(String id) {
        Contact contact = repository.findById(id).orElseThrow(() -> BaseException.notFound(Contact.class.getSimpleName(), "id", id));
        return mapper.mapToResponse(contact);
    }

    @Override
    public ContactResponse update(String id, ContactRequest request) {
        Contact contact = repository.findById(id).orElseThrow(() -> BaseException.notFound(Contact.class.getSimpleName(), "id", id));
        Contact contact1 = mapper.map(request, contact);
        repository.save(contact1);
        return mapper.mapToResponse(contact1);
    }

    @Override
    public List<ContactResponse> getAll() {
        List<Contact> contacts = repository.findAll();
        return contacts.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.findById(id).orElseThrow(() -> BaseException.notFound(Contact.class.getSimpleName(), "id", id));
        repository.deleteById(id);
    }

    @Override
    public void post(ContactInfoRequest contactInfoRequest) {
        log.info("ActionLog started post user email : " + contactInfoRequest.getEmail());
        try {
            ContactInfo contactInfo = contactInfoMapper.mapToEntity(contactInfoRequest);
            contactInfoRepo.save(contactInfo);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post user email : " + contactInfoRequest.getEmail());

    }

    @Override
    public List<ContactInfoResponse> getAllContactInfo() {
        log.info("ActionLog started getAll");
        List<ContactInfo> contactInfoResponses = contactInfoRepo.findAll();
        log.info("ActionLog end getAll");
        return contactInfoResponses.stream().map(contactInfoMapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void update(String id, ContactInfoRequestUpdate contactInfoRequestUpdate) {
        log.info("ActionLog started id :" + id);
        ContactInfo contactInfo = contactInfoRepo.findById(id).orElseThrow(() -> BaseException.notFound(ContactInfo.class.getSimpleName(), "id", id.toString()));
        try {
            if (contactInfoRequestUpdate.getLocation() != null && !contactInfoRequestUpdate.getLocation().isEmpty()) {
                contactInfo.setLocation(contactInfoRequestUpdate.getLocation());
            }
            if (contactInfoRequestUpdate.getFirstNumber() != null && !contactInfoRequestUpdate.getFirstNumber().isEmpty()) {
                contactInfo.setFirstNumber(contactInfoRequestUpdate.getFirstNumber());
            }
            if (contactInfoRequestUpdate.getSecondNumber() != null && !contactInfoRequestUpdate.getSecondNumber().isEmpty()) {
                contactInfo.setSecondNumber(contactInfoRequestUpdate.getSecondNumber());
            }
            if (contactInfoRequestUpdate.getEmail() != null && !contactInfoRequestUpdate.getEmail().isEmpty()) {
                contactInfo.setEmail(contactInfoRequestUpdate.getEmail());
            }
            contactInfoRepo.save(contactInfo);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end id :" + id);
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog start id :" + id);
        try {
            contactInfoRepo.deleteById(id);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end id :" + id);
    }
}
