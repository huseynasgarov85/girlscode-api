package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectsRepository extends MongoRepository<Projects,String> {
}
