package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.FAQ;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FAQRepository extends MongoRepository<FAQ,String> {
}
