package com.example.girlscodeapi.model.repo;


import com.example.girlscodeapi.model.entity.Partners;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartnersRepo extends MongoRepository<Partners, String> {
}
