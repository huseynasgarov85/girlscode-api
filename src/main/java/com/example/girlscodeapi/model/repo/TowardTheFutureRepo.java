package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.TowardTheFuture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TowardTheFutureRepo extends MongoRepository<TowardTheFuture, String> {
}
