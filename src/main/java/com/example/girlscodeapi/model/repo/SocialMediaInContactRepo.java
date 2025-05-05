package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.SocialMediaInContact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocialMediaInContactRepo extends MongoRepository<SocialMediaInContact, String> {
}
