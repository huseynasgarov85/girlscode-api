package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.ContactInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactInfoRepo extends MongoRepository<ContactInfo, String> {
}
