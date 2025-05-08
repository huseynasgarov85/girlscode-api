package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.InputFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InputFileRepository extends MongoRepository<InputFile, Long> {
}
