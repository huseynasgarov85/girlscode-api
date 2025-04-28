package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.InfoForWomen;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfoForWomenRepository extends MongoRepository<InfoForWomen,String> {
}
