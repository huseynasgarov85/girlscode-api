package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.CoverPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepo extends MongoRepository<CoverPhoto, String> {

}
