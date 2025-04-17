package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.SuccessStory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuccessStoryRepo extends MongoRepository<SuccessStory, String> {
}
