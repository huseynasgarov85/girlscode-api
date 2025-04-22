package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact,String> {
}
