package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Programs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgramsRepository extends MongoRepository<Programs,String> {

}
