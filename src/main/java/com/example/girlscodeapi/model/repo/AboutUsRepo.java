package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.AboutUs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutUsRepo extends MongoRepository<AboutUs, String> {
}
