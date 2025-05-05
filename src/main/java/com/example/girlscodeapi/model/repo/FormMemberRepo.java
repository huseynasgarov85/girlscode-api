package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.FormMember;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormMemberRepo extends MongoRepository<FormMember, String> {
}
