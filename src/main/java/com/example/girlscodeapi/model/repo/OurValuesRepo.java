package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.OurValues;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OurValuesRepo extends MongoRepository<OurValues, String> {
}
