package com.example.girlscodeapi.service.contact;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ContactMapper;
import com.example.girlscodeapi.model.dto.request.ContactRequest;
import com.example.girlscodeapi.model.dto.response.ContactResponse;
import com.example.girlscodeapi.model.entity.Contact;
import com.example.girlscodeapi.model.repo.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{
    private final ContactMapper mapper;
    private final ContactRepository repository ;
    @Override
    public ContactResponse add(ContactRequest request) {
        Contact contact=mapper.mapToEntity(request);
        repository.save(contact);
        return mapper.mapToResponse(contact);
    }

    @Override
    public ContactResponse getById(String id) {
        Contact contact=repository.findById(id).orElseThrow(()-> BaseException.notFound(Contact.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(contact);
    }

    @Override
    public ContactResponse update(String id, ContactRequest request) {
        Contact contact=repository.findById(id).orElseThrow(()-> BaseException.notFound(Contact.class.getSimpleName(),"id",id));
        Contact contact1=mapper.map(request,contact);
        repository.save(contact1);
        return mapper.mapToResponse(contact1);
    }

    @Override
    public List<ContactResponse> getAll() {
        List<Contact> contacts=repository.findAll();
        return contacts.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.findById(id).orElseThrow(()->BaseException.notFound(Contact.class.getSimpleName(),"id",id));
        repository.deleteById(id);
    }
}
